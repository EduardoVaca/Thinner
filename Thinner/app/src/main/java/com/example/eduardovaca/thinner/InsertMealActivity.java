package com.example.eduardovaca.thinner;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class InsertMealActivity extends AppCompatActivity {

    private EditText name;
    private EditText units;
    private EditText quantity;
    private EditText time;
    private EditText day;
    private Button addDish;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_meal);

        name = (EditText) findViewById(R.id.name_food_et);
        units= (EditText) findViewById(R.id.units_food_et);
        quantity = (EditText) findViewById(R.id.quantity_food_et);
        time = (EditText) findViewById(R.id.time_food_et);
        day = (EditText) findViewById(R.id.day_food_et);
        addDish = (Button) findViewById(R.id.add_dish_btn);
        finish = (Button) findViewById(R.id.finish_btn);

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
                    newDish.put("time", time.getText().toString());
                    newDish.put("day", day.getText().toString());

                    final ProgressDialog load = new ProgressDialog(InsertMealActivity.this, AlertDialog.THEME_HOLO_LIGHT);
                    load.show();

                    newDish.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Log.v(null, "DONE");
                                Toast.makeText(getApplicationContext(), "Dish added!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    load.dismiss();
                }

            }
        });





    }
}
