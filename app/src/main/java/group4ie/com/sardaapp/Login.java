package group4ie.com.sardaapp;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

//LOGIN_URL = "http://sarda.comule.com/Login.php";


public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText usernameField,passwordField;
    Button loginBut;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view) // for back button
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

    public void onClickLogin(View v) // when you click the login button
    {
        if(v.getId() == R.id.loginButton)
        {
            EditText a = (EditText)findViewById(R.id.userName);
            String user = a.getText().toString();

            EditText b = (EditText)findViewById(R.id.PasswordText);
            String pass = b.getText().toString();
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