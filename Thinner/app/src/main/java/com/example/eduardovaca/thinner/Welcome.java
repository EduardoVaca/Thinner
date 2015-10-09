package com.example.eduardovaca.thinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by eduardovaca on 07/10/15.
 */
public class Welcome extends Activity {

    // Declare Variable
    private TextView logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.welcome);

        logout = (TextView) findViewById(R.id.logout);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();



        // Logout Button Click Listener
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Logout current user
                ParseUser.logOut();
                finish();
            }
        });
    }
}
