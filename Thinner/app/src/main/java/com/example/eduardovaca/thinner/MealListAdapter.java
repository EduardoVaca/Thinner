package com.example.eduardovaca.thinner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by eduardovaca on 22/11/15.
 */
public class MealListAdapter extends ArrayAdapter<ParseObject> {

    Context context;
    int layoutResourceId;
    ParseObject data[] = null;
    String day;

    public MealListAdapter(Context context, int layoutResourceId, ParseObject[] data, String day) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.day = day;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View row = convertView;
        MealHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new MealHolder();
            holder.name = (TextView)row.findViewById(R.id.meal_name);
            holder.time = (TextView) row.findViewById(R.id.day_time);
            holder.icon = (ImageView) row.findViewById(R.id.day_icon);
            holder.quantity = (TextView) row.findViewById(R.id.meal_quantity);


            row.setTag(holder);

        }
        else
        {
            holder = (MealHolder) row.getTag();
        }

        final ParseObject meal = data[position];

        holder.name.setText(meal.getString("name"));
        holder.time.setText(meal.getString("time"));
        holder.quantity.setText(meal.getString("quantity") + " " + meal.getString("units"));

        if(day.equals("Monday")){
            holder.icon.setImageResource(R.drawable.monday_icon);
        }else if(day.equals("Tuesday")){
            holder.icon.setImageResource(R.drawable.tuesday_icon);
        }else if(day.equals("Wednesday")){
            holder.icon.setImageResource(R.drawable.wednesday_icon);
        }else if(day.equals("Thursday")){
            holder.icon.setImageResource(R.drawable.thursday_icon);
        }else if(day.equals("Friday")){
            holder.icon.setImageResource(R.drawable.friday_icon);
        }else if(day.equals("Saturday")){
            holder.icon.setImageResource(R.drawable.saturday_icon);
        }else if(day.equals("Sunday")){
            holder.icon.setImageResource(R.drawable.sunday_icon);
        }



        return row;
    }



    static class MealHolder
    {
        TextView name;
        TextView time;
        TextView quantity;
        ImageView icon;
    }

}
