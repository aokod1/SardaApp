package group4ie.com.sardaapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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


public class SignUp extends Activity implements View.OnClickListener {

    String name;
    String id;
    InputStream is=null;
    String result=null;
    String line=null;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onClick(View view)// for back button
    {
        if (view == findViewById(R.id.BackTwo)) {
            Intent b = new Intent(this, MainActivity.class);
            startActivity(b);
        }
    }

    public void onClickSignUp(View v) {
        EditText Name = (EditText) findViewById(R.id.Name);
        EditText Email = (EditText) findViewById(R.id.email);
        EditText Password = (EditText) findViewById(R.id.Password);
        EditText Password2 = (EditText) findViewById(R.id.Password2);
        EditText UserName = (EditText) findViewById(R.id.userName);

        String nameStr = Name.getText().toString();
        String emailStr = Email.getText().toString();
        String passStr = Password.getText().toString();
        String passStr2 = Password2.getText().toString();
        String userStr = UserName.getText().toString();

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


        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("Name", nameStr));
        nameValuePairs.add(new BasicNameValuePair("Email", emailStr));
        nameValuePairs.add(new BasicNameValuePair("User_Name", userStr));
        nameValuePairs.add(new BasicNameValuePair("User_Password", passStr));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("winmysqls02.cpt.wa.co.za/index.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            code=(json_data.getInt("code"));

            if(code==1)
            {
                Toast.makeText(getBaseContext(), "Inserted Successfully",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
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
