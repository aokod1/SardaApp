package group4ie.com.sardaapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends ActionBarActivity {

    private EditText usernameField,passwordField;
    private TextView status,role,method;
    EditText mEdit;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = (EditText)findViewById(R.id.userName);
        passwordField = (EditText)findViewById(R.id.PasswordText);
    }


    public void onClick(View view)
    {
        if (view == findViewById(R.id.Back))
        {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    public void onClickLogin(View v)
    {
        if(v.getId() == R.id.loginButton)
        {
            EditText a = (EditText)findViewById(R.id.userName);
            String str = a.getText().toString();

            EditText b = (EditText)findViewById(R.id.PasswordText);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);

            if(pass.equals(password))
            {
                Toast pas = Toast.makeText(Login.this,"SIGN IN SUCCESFULL!",Toast.LENGTH_SHORT);
                pas.show();
            } else if(!pass.equals(password))
            {
                Toast pas = Toast.makeText(Login.this,"FAILED",Toast.LENGTH_SHORT);
                pas.show();
            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}