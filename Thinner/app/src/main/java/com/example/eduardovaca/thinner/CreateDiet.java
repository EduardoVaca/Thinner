package com.example.eduardovaca.thinner;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class CreateDiet extends AppCompatActivity {

    private EditText dietName;
    private Button createDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diet);

        dietName = (EditText) findViewById(R.id.diet_name_et);
        createDiet = (Button) findViewById(R.id.create_diet);

        createDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(null, "Button pressed");
                ParseUser currentUser = ParseUser.getCurrentUser();

                final ParseObject newDiet = new ParseObject("Diet");
                newDiet.put("userId", currentUser);
                newDiet.put("name", dietName.getText().toString());

                final ProgressDialog load = new ProgressDialog(CreateDiet.this, AlertDialog.THEME_HOLO_LIGHT);
                load.show();

                newDiet.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e == null){
                            Log.v(null, "Inserted");
                            Intent intent = new Intent(CreateDiet.this, InsertMealActivity.class);
                            intent.putExtra("OBJECT_ID", newDiet.getObjectId());
                            startActivity(intent);
                            finish();
                        }
                        load.dismiss();
                    }
                });


            }
        });
    }
}
