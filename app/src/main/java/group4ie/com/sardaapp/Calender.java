package group4ie.com.sardaapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class Calender extends ActionBarActivity implements View.OnClickListener {

    String myJSON;
    private static final String TAG_RESULTS="result";
    private static final String TAG_Event_Name="Event_Name";
    private static final String TAG_Event_description = "Event_description";
    private static final String TAG_Event_date = "Event_date";
    private static final String TAG_Event_venue ="Event_venue";
    private static final String TAG_Event_ID ="Event_ID";
    private static final String TAG_Event_Time ="Event_Time";

    JSONArray events = null; // peoples
    ArrayList<HashMap<String, String>> eventList; // personlist

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        list = (ListView) findViewById(R.id.ev);
        eventList = new ArrayList<HashMap<String,String>>(); // Personlist
        getData();
    }

    public void getData() {
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://sarda.comule.com/list.php");
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;

                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e)
                {

                    System.out.println("Im not reading");
                }
                finally
                {
                    try {
                        if (inputStream != null) inputStream.close();
                    } catch (Exception squish) {
                    }
                }
                return result;
            }
            @Override
            protected void onPostExecute(String result)
            {
                myJSON=result;
                showList();
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            events = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < events.length(); i++) {
                JSONObject c = events.getJSONObject(i);
                String Name = c.getString(TAG_Event_Name);
                String Description = c.getString(TAG_Event_description);
                String Date = c.getString(TAG_Event_date);
                String Venue = c.getString(TAG_Event_venue);
                String ID = c.getString(TAG_Event_ID);
                String Time = c.getString(TAG_Event_Time);

                HashMap<String, String> eventtt = new HashMap<String, String>();

                eventtt.put(TAG_Event_Name, Name);
                eventtt.put(TAG_Event_description, Description);
                eventtt.put(TAG_Event_date, Date);
                eventtt.put(TAG_Event_venue, Venue);
                eventtt.put(TAG_Event_ID, ID);
                eventtt.put(TAG_Event_Time, Time);


                eventList.add(eventtt);
            }
            ListAdapter adapter = new SimpleAdapter
                    (
                            Calender.this, eventList, R.layout.eventt,
                            new String[]{TAG_Event_Name, TAG_Event_description, TAG_Event_date, TAG_Event_venue, TAG_Event_ID, TAG_Event_Time},
                            new int[]{R.id.Name, R.id.Description, R.id.Date, R.id.Venue, R.id.ID, R.id.Time}
                    );
            list.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("It failed to display items in list");

            System.out.println("Checking if they assigned correctly");

            if(eventList.isEmpty())
            {
                System.out.println("Im empty as shit");
            }else
            {
                System.out.println(eventList);
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
        {
            // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calender, menu);
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

    public void onClick(View view)// for back button
    {
        if (view == findViewById(R.id.Back)) {
            Intent b = new Intent(this, MainActivity.class);
            startActivity(b);
        }
    }

//http://www.simplifiedcoding.net/android-json-parsing-retrieve-from-mysql-database/
}

