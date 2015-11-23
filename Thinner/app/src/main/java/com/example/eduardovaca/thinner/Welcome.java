package com.example.eduardovaca.thinner;

import android.app.Activity;
import android.content.Intent;
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
    private Button createDiet;
    private Button following;
    private Button myDiets;
    private Button discover;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.welcome);

        logout = (TextView) findViewById(R.id.logout);
        createDiet = (Button) findViewById(R.id.create_diet);
        following = (Button) findViewById(R.id.following);
        myDiets = (Button) findViewById(R.id.my_diets);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        createDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, CreateDiet.class);
                startActivity(intent);
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, DaysActivity.class);
                startActivity(intent);
            }
        });

        myDiets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, MyDietsActivity.class);
                startActivity(intent);
            }
        });

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
