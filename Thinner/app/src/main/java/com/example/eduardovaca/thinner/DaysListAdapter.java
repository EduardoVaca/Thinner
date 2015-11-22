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
public class DaysListAdapter  extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    String data[] = null;

    public DaysListAdapter(Context context, int layoutResourceId, String[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    public View getView(int position, View convertView, ViewGroup parent) {


        View row = convertView;
        DayHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.item_days, parent, false);

            holder = new DayHolder();
            holder.bg = (ImageView) row.findViewById(R.id.day_bg);


            row.setTag(holder);

        }
        else
        {
            holder = (DayHolder) row.getTag();
        }

        if(data[position].equals("Monday")){
            holder.bg.setImageResource(R.drawable.monday);
        }else if(data[position].equals("Tuesday")){
            holder.bg.setImageResource(R.drawable.tuesday);
        }else if(data[position].equals("Wednesday")){
            holder.bg.setImageResource(R.drawable.wednesday);
        }else if(data[position].equals("Thursday")){
            holder.bg.setImageResource(R.drawable.thursday);
        }else if(data[position].equals("Friday")){
            holder.bg.setImageResource(R.drawable.friday);
        }else if(data[position].equals("Saturday")){
            holder.bg.setImageResource(R.drawable.saturday);
        }else if(data[position].equals("Sunday")){
            holder.bg.setImageResource(R.drawable.sunday);
        }

        return row;
    }


    static class DayHolder
    {
        ImageView bg;
    }
}
