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

public class MyDietsActivity extends AppCompatActivity {

    private ListView myDietsList;
    private ProgressDialog loading;
    private ParseObject[] objects;
    private static final String DIET_CLASS = "Diet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diets);

        myDietsList = (ListView) findViewById(R.id.my_diets_list);

        ParseQuery<ParseObject> query = new ParseQuery<>(DIET_CLASS);
        query.whereEqualTo("userId", ParseUser.getCurrentUser());

        loading = new ProgressDialog(MyDietsActivity.this, AlertDialog.THEME_HOLO_LIGHT);
        loading.setMessage("Cargando");
        loading.show();

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                loading.hide();
                if (e == null) {
                    objects = list.toArray(new ParseObject[list.size()]);
                    DietListAdapter dietAdapter = new DietListAdapter(MyDietsActivity.this, R.layout.item_my_diets, objects);
                    myDietsList.setAdapter(dietAdapter);
                }
            }
        });

    }
}
