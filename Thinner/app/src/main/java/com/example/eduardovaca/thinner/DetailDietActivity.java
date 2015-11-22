package com.example.eduardovaca.thinner;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class DetailDietActivity extends AppCompatActivity {

    private ListView mealsList;
    private ProgressDialog loading;
    private ParseObject[] objects;

    private static final String CLASS_MEAL = "Dish";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diet);

        mealsList = (ListView) findViewById(R.id.meals_list);

        loading = new ProgressDialog(DetailDietActivity.this, AlertDialog.THEME_HOLO_LIGHT);
        loading.setMessage("Cargando");
        loading.show();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(CLASS_MEAL);
        query.whereEqualTo("day", getIntent().getStringExtra("DAY"));
        query.whereEqualTo("dietId", ParseUser.getCurrentUser().getString("actualDiet"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                loading.hide();
                objects = list.toArray(new ParseObject[list.size()]);
                MealListAdapter mealAdapter = new MealListAdapter(DetailDietActivity.this, R.layout.item_meal, objects ,
                                                        getIntent().getStringExtra("DAY"));
                mealsList.setAdapter(mealAdapter);
            }
        });




    }
}
