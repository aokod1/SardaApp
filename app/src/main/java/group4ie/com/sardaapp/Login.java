package group4ie.com.sardaapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.net.ssl.HttpsURLConnection;

public class Login extends Activity implements View.OnClickListener{

    //
    public static final String USER_NAME = "USER_NAME";
    public static final String PASSWORD = "PASSWORD";
    private static final String LOGIN_URL = "http://sarda.comule.com/login.php";

    private EditText UserName;
    private EditText Password;
    private Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        bLogin = (Button) findViewById(R.id.login);
    }

    public void onClickLogin()
    {

        UserName = (EditText)findViewById(R.id.userName);
        Password = (EditText)findViewById(R.id.PasswordText);

        String user = UserName.getText().toString().trim();
        String pass = Password.getText().toString().trim();

        userLogin(user,pass);

    }

    private void userLogin(final String username, final String password)
    {
        System.out.println(username);
        System.out.println(password);

        class UserLoginClass extends AsyncTask<String, Void, String>
        {
            protected String doInBackground(String... params)
            {
                UserName = (EditText)findViewById(R.id.userName);
                Password = (EditText)findViewById(R.id.PasswordText);

                String user = UserName.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                HashMap<String,String> data = new HashMap<>();
                data.put("username",user);
                data.put("password",pass);

                String result = "";
                //String result = sendPostRequest(LOGIN_URL, data);
                return result;


            }
            //System.out.println("");









            @Override
            protected void onPostExecute(String s)
            {
               // super.onPostExecute(s);
               // loading.dismiss();
                if(s.equalsIgnoreCase("success"))
                {
                    Intent intent = new Intent(Login.this,Home.class);
                   // intent.putExtra(USER_NAME,username);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,s,Toast.LENGTH_LONG).show();
                }
            }



        }// end of async task
    }// user Login




    public void onClick(View view)
    {

    }
}

