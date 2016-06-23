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

        Venues place = getItem(position);
        String icon = place.getCategories().getCategoriesList().get(position).getIcon().getPrefix()
                +place.getCategories().getCategoriesList().get(position).getIcon().getPrefix();
        String distance = String.valueOf(Math.round(place.getLocation().getDistance()*.01)/10.0)+" miles away";

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout_main, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.name_textView);
        TextView distanceTextView = (TextView) convertView.findViewById(R.id.distance_textView);
        TextView openStatusTextView = (TextView) convertView.findViewById(R.id.textview_status);
        ImageView placeImageImageView = (ImageView) convertView.findViewById(R.id.imageView_mainActivity);

        nameTextView.setText(place.getName());
        distanceTextView.setText(distance);
       // TODO: Add logic to determine if a store is open or closed, based on its hours verses the system time.
        openStatusTextView.setText("OPEN");
        Picasso.with(getContext())
                .load(icon)
                .into(placeImageImageView);

        return convertView;
    }

}
