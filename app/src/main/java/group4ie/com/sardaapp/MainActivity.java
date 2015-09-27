package group4ie.com.sardaapp;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends ActionBarActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void onClick(View view)
    {
        if(view == (View) findViewById(R.id.login)) // code used to check which button has been clicked
        {
            Intent i = new Intent(this,Login.class); // Like making an object of the class ("Activity you want to call")
            startActivity(i);
        }

        else if(view == (View) findViewById(R.id.SignUp))// this  signUp is the name of the button.
        {
            Intent i = new Intent(this,SignUp.class);
            startActivity(i);
        }

        else if(view == (View) findViewById(R.id.bout))
        {
            Intent i = new Intent(this,AboutUs.class);
            startActivity(i);
        }

        else if(view == (View) findViewById(R.id.calender))
        {
            Intent i = new Intent(this,Calender.class);
            startActivity(i);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
