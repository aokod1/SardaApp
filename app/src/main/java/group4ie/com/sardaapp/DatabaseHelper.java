package group4ie.com.sardaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_USERNAME = "User Name";
    private static final String COLUMN_PASSWORD = "Password";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null  ," + "Name text not null, Email text not null, User Name text not null, Password text not null)";



    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }

    public void insertContatc(Contact c)
    {
      db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT *  from contacts";
        Cursor cursor = db.rawQuery(query,null);

        int count = cursor.getCount();
        values.put(COLUMN_ID, count);

        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASSWORD, c.getPass());
        values.put(COLUMN_USERNAME, c.getUser());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String user)
    {
        db = this.getReadableDatabase();
        String query = "select User Name, Password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        String a,b;
        b = "Not Found";

        if (cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                 if(a.equals(user))
                 {
                     b = cursor.getString(1);
                     break;
                 }

            }while(cursor.moveToNext());
        }

        return b;
    }


}
