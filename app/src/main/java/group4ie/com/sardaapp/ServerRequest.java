package group4ie.com.sardaapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by akodi on 28-Sep-15.
 */
public class ServerRequest
{

    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000*15;
    public static final String SERVER_ADDRESS = "https://winmysqls02.cpt.wa.co.za/" ;




    public ServerRequest(Context context)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait......");
    }

    public void storeUserDataInBackground(Contact user, GetUserCallBack userCallback)
    {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback ).execute();

    }

    public void fetchUserDataInBackground(Contact user, GetUserCallBack userCallback)
    {
        progressDialog.show();


    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void,Void, Void>
    {
        Contact user;
        GetUserCallBack userCallBack;

        public StoreUserDataAsyncTask(Contact user, GetUserCallBack userCallback)
        {
            this.user=user;
            this.userCallBack = userCallback;
        }

        @Override
        protected Void doInBackground(Void... params)
        {



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            progressDialog.dismiss();
            userCallBack.done(null);



            super.onPostExecute(aVoid);
        }




    }




}
