package com.example.eduardovaca.thinner;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertMealActivity extends AppCompatActivity {

    private EditText name;
    private EditText units;
    private EditText quantity;
    private Button addDish;
    private Button finish;
    private TextView listTV;
    private Spinner timeSpinner;
    private Spinner daySpinner;

    private static final String daysWeek [] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static final String timeDay [] = {"Dawn", "Morning", "Noon", "Afternoon", "Evening"};

    String list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_meal);

        name = (EditText) findViewById(R.id.name_food_et);
        units= (EditText) findViewById(R.id.units_food_et);
        quantity = (EditText) findViewById(R.id.quantity_food_et);
        addDish = (Button) findViewById(R.id.add_dish_btn);
        finish = (Button) findViewById(R.id.finish_btn);
        listTV  = (TextView) findViewById(R.id.list_dishes);
        timeSpinner = (Spinner) findViewById(R.id.time_spinner);
        daySpinner = (Spinner) findViewById(R.id.day_spinner);
        list = "";

        List<String> dayList = new ArrayList<>(Arrays.asList(daysWeek));
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(InsertMealActivity.this,
                                                    android.R.layout.simple_spinner_item, dayList);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        List<String> timeList = new ArrayList<>(Arrays.asList(timeDay));
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(InsertMealActivity.this,
                                                    android.R.layout.simple_spinner_item, timeList);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);

        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getIntent().hasExtra("OBJECT_ID")){

                    Log.v(null, "ID: " + getIntent().getStringExtra("OBJECT_ID"));
                    ParseObject newDish = new ParseObject("Dish");
                    newDish.put("dietId", getIntent().getStringExtra("OBJECT_ID"));
                    newDish.put("name", name.getText().toString());
                    newDish.put("units", units.getText().toString());
                    newDish.put("quantity", quantity.getText().toString());
                    newDish.put("time", timeSpinner.getSelectedItem().toString());
                    newDish.put("day", daySpinner.getSelectedItem().toString());

                    final ProgressDialog load = new ProgressDialog(InsertMealActivity.this, AlertDialog.THEME_HOLO_LIGHT);
                    load.show();

                    newDish.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Log.v(null, "DONE");
                                Toast.makeText(getApplicationContext(), "Dish added!", Toast.LENGTH_SHORT).show();
                                list += name.getText().toString() + " - " + daySpinner.getSelectedItem().toString() + " - "
                                        + timeSpinner.getSelectedItem().toString() + "\n";

                                listTV.setText(list);
                                name.setText("");
                                units.setText("");
                                quantity.setText("");
                            }
                        }
                    });

                    load.dismiss();
                }

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertMealActivity.this, Welcome.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });





    }
}
