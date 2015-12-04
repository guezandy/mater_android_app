package com.lunadeveloper.test1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class Test2 extends ActionBarActivity {

    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        welcome = (TextView) findViewById(R.id.welcome_text);

        ParseUser theUser = ParseUser.getCurrentUser();

        welcome.setText("Welcome "+ theUser.getString("name")+ " to the app");
        //i can use parse here
        /*ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar"); //bar inside column foo
        testObject.put("firstName", "Andrew");
        testObject.put("lastname", "Rodriguez");
        testObject.saveInBackground();

        ParseObject person = new ParseObject("Person");
        person.put("firstname", "Raul");
        person.put("lastname", "LaRosita");
        person.put("job", "boeing");
        person.saveInBackground();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test2, menu);
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
