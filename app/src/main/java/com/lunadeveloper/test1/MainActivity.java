package com.lunadeveloper.test1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    String username = "aaa";
    String password = "bbb";

    EditText username_input;
    EditText password_input;
    Button login_button;
    TextView success;
    Button register;
    EditText name_input;

    //first thing to run
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "eMXAWdhOQYCjy8ReNgUxr00r9hhBcUVU6Jy8c1uM", "WueQuYUTOKWaMcuAXbqgviNF7WeEc8hzUy2ee2IP");

        username_input = (EditText) findViewById(R.id.username);
        password_input = (EditText) findViewById(R.id.password);
        login_button = (Button) findViewById(R.id.login);
        success = (TextView) findViewById(R.id.success);
        register = (Button) findViewById(R.id.register);
        name_input = (EditText) findViewById(R.id.name);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_text = username_input.getText().toString();
                String pass_text = password_input.getText().toString();

                ParseUser.logInInBackground(user_text, pass_text, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Toast.makeText(getBaseContext(), "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, Test2.class);
                            startActivity(i);
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                        }
                    }
                });

                /*System.out.println("Usertext: "+user_text+ " pass_text: "+ pass_text);
                if(user_text.equals(username) && pass_text.equals(password)) {
                    success.setText("LOGIN SUCCESSFUL");
                    Intent i = new Intent(MainActivity.this, Test2.class);
                    startActivity(i);
                } else {
                    success.setText("LOGIN FAILED");
                }*/
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_text = username_input.getText().toString();
                String pass_text = password_input.getText().toString();
                String name_text = name_input.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(user_text);
                user.setPassword(pass_text);
                user.put("name", name_text);
//                user.setEmail("email@example.com");
// other fields can be set just like with ParseObject
//                user.put("phone", "650-253-0000");
//                user.put("school", "mater");

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Toast.makeText(getBaseContext(), "Registered successful", Toast.LENGTH_LONG).show();
                            username_input.setText("");
                            password_input.setText("");
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Person");
        //query.whereEqualTo("playerName", "Dan Stemkoski");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> people, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + people.size() + " people");
                    String output = "";
                    for(int i = 0; i < people.size(); i++) {
                        output = output+
                                people.get(i).getString("firstname")+
                                " "+people.get(i).getString("lastname") +
                                " works at "+people.get(i).getString("job")+
                                "\n";
                        System.out.println(people.get(i).getString("firstname")+ " "+people.get(i).getString("lastname") + " works at "+people.get(i).getString("job"));
                    }
                    success.setText(output);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

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
        } else if(id == R.id.share) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
