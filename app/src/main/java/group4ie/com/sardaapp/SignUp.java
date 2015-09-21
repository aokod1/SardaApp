package group4ie.com.sardaapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUp extends Activity {

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    public void onClickSignUp(View v)
    {
        if(v.getId()==R.id.signUpButton) // checks if signup button was clicked
        {
            EditText Name = (EditText)findViewById(R.id.Name);
            EditText Email = (EditText)findViewById(R.id.email);
            EditText Password = (EditText)findViewById(R.id.Password);
            EditText Password2 = (EditText)findViewById(R.id.Password2);
            EditText UserName = (EditText)findViewById(R.id.userName);

            String nameStr = Name.getText().toString();
            String emailStr = Email.getText().toString();
            String passStr = Password.getText().toString();
            String passStr2 = Password2.getText().toString();
            String userStr = UserName.getText().toString();

            if(!passStr.equals(passStr2))
            {
                //pop up Message if passwords dont match
                Toast pass = Toast.makeText(SignUp.this,"Passwords dont match!",Toast.LENGTH_SHORT);
                pass.show();

            } else if(passStr.equals(passStr2))
            {
                Toast pass = Toast.makeText(SignUp.this,"Passwords Match!",Toast.LENGTH_SHORT);
                pass.show();
                //insert details in database
                 Contact c = new Contact();
                c.setName(nameStr);
                c.setEmail(emailStr);
                c.setPass(passStr);
                c.setUser(userStr);
                helper.insertContatc(c);





            }
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
