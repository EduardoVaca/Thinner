package com.example.eduardovaca.thinner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by eduardovaca on 22/11/15.
 */
public class DietListAdapter extends ArrayAdapter<ParseObject> {

    Context context;
    int layoutResourceId;
    ParseObject data[] = null;

    public DietListAdapter(Context context, int layoutResourceId, ParseObject[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View row = convertView;
        DietHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.item_my_diets, parent, false);

            holder = new DietHolder();
            holder.name = (TextView)row.findViewById(R.id.name_diet);
            holder.date = (TextView) row.findViewById(R.id.diet_date);


            row.setTag(holder);

        }
        else
        {
            holder = (DietHolder) row.getTag();
        }

        final ParseObject diet = data[position];

        holder.name.setText(diet.getString("name"));
        String dateArray[] = diet.getCreatedAt().toString().split(" ");
        holder.date.setText(dateArray[0] + " " + dateArray[1] + " " + dateArray[2]);

        return row;
    }



    static class DietHolder
    {
        TextView name;
        TextView date;
    }
}



