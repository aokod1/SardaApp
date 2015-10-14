package group4ie.com.sardaapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;


public class SignUp extends Activity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onClick(View view)// for back button
    {
        if (view == findViewById(R.id.BackTwo))
        {
            Intent b = new Intent(this, MainActivity.class);
            startActivity(b);
        }
    }

    // Sign up button
    public void onClickSignUp(View v)
    {

        EditText Name = (EditText) findViewById(R.id.Name);
        EditText Email = (EditText) findViewById(R.id.email);
        EditText Password = (EditText) findViewById(R.id.Password);
        EditText Password2 = (EditText) findViewById(R.id.Password2);
        EditText UserName = (EditText) findViewById(R.id.userName);
        EditText Day = (EditText) findViewById(R.id.day);

        String nameStr = Name.getText().toString();
        String emailStr = Email.getText().toString();
        String passStr = Password.getText().toString();
        String passStr2 = Password2.getText().toString();
        String userStr = UserName.getText().toString();
        String dayStr = Day.getText().toString();

        if (passStr.equals(passStr2) && !passStr.equals("") || !passStr2.equals("")) {
            Toast pass = Toast.makeText(SignUp.this, "Passwords Match!", Toast.LENGTH_SHORT);
            pass.show();
        } else if (!passStr.equals(passStr2)) {
            Toast pass = Toast.makeText(SignUp.this, "Passwords dont match or no password entered", Toast.LENGTH_SHORT);
            Password.setHintTextColor(Color.RED);
            Password2.setHintTextColor(Color.RED);
            pass.show();
        } else if (passStr.equals("") || passStr2.equals("")) {
            Toast pass = Toast.makeText(SignUp.this, "No password entered", Toast.LENGTH_SHORT);
            Password.setHintTextColor(Color.RED);
            Password2.setHintTextColor(Color.RED);
            pass.show();
        }

        if (nameStr.equals("")) {
            Name.setHintTextColor(Color.RED);
            Toast pass = Toast.makeText(SignUp.this, "Name field empty!", Toast.LENGTH_SHORT);
            pass.show();
        }

        if (emailStr.equals("")) {
            Email.setHintTextColor(Color.RED);
            Toast pass = Toast.makeText(SignUp.this, "Email field empty!", Toast.LENGTH_SHORT);
            pass.show();
        }

        if (userStr.equals("")) {
            UserName.setHintTextColor(Color.RED);
            Toast pass = Toast.makeText(SignUp.this, "User Name field empty!", Toast.LENGTH_SHORT);
            pass.show();
        }


        insertToDatabase(emailStr,nameStr,userStr,passStr,dayStr);
    }

    private void insertToDatabase(final String email, String name,String user,String pass,String dy)
    {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                EditText Name = (EditText) findViewById(R.id.Name);
                EditText Email = (EditText) findViewById(R.id.email);
                EditText Password = (EditText) findViewById(R.id.Password);
                EditText Password2 = (EditText) findViewById(R.id.Password2);
                EditText UserName = (EditText) findViewById(R.id.userName);
                EditText Day = (EditText) findViewById(R.id.day);

                String nameSt;
                String emailSt;
                String passSt;
                String userSt;
                String dayStr;

                nameSt = Name.getText().toString();
                emailSt = Email.getText().toString();
                passSt = Password.getText().toString();
                userSt = UserName.getText().toString();
                dayStr = Day.getText().toString();


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("Email", emailSt));
                nameValuePairs.add(new BasicNameValuePair("Name", nameSt));
                nameValuePairs.add(new BasicNameValuePair("Day", dayStr));
                nameValuePairs.add(new BasicNameValuePair("User_Name", userSt));
                nameValuePairs.add(new BasicNameValuePair("User_Password", passSt));


                try {

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://sarda.comule.com/insert.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast pss = Toast.makeText(SignUp.this,result, Toast.LENGTH_SHORT);
                pss.show();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(email, name,user,pass);

        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
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
