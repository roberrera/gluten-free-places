package com.roberterrera.glutenfreeplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.roberterrera.glutenfreeplaces.model.Venues;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rob on 6/17/16.
 */
public class ListItemAdapter extends ArrayAdapter<Venues> {

    public ListItemAdapter(Context context, ArrayList<Venues> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Venues place = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout_main, parent, false);
        }
        // Lookup view for data population
        TextView nameTextView = (TextView) convertView.findViewById(R.id.name_textView);
        TextView distanceTextView = (TextView) convertView.findViewById(R.id.distance_textView);
        TextView openStatusTextView = (TextView) convertView.findViewById(R.id.textview_status);
        ImageView placeImageImageView = (ImageView) convertView.findViewById(R.id.imageView_mainActivity);

        nameTextView.setText(getItem(position).getName());
        distanceTextView.setText(String.valueOf(Math.round(getItem(position).getLocation().getDistance()*.01)/10.0)+" miles away");
        openStatusTextView.setText("OPEN");
//        Picasso.with(getContext())
//                .load(getItem(position).getCategories().getIcon().getPrefix()
//                        +getItem(position).getCategories().getIcon().getPrefix())
//                .into(placeImageImageView);

        // Return the completed view to render on screen
        return convertView;
    }

}
