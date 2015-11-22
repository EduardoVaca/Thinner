package com.example.eduardovaca.thinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DaysActivity extends AppCompatActivity {

    private ListView daysList;

    private static final String daysWeek [] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);

        daysList = (ListView) findViewById(R.id.days_list);

        DaysListAdapter dayAdapter = new DaysListAdapter(DaysActivity.this, R.layout.item_days, daysWeek);
        daysList.setAdapter(dayAdapter);


        daysList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DaysActivity.this, DetailDietActivity.class);
                intent.putExtra("DAY", daysWeek[position]);
                startActivity(intent);
            }
        });

    }
}
