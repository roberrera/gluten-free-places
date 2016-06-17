package com.roberterrera.glutenfreeplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rob on 6/17/16.
 */
public class ListItemAdapter extends ArrayAdapter<Place> {
    public ListItemAdapter(Context context, ArrayList<Place> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Place place = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout_main, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.name_textView);
        TextView distance = (TextView) convertView.findViewById(R.id.distance_textView);
        TextView category = (TextView) convertView.findViewById(R.id.category_textView);
        TextView openStatus = (TextView) convertView.findViewById(R.id.textview_status);
        ImageView placeImage = (ImageView) convertView.findViewById(R.id.imageView_mainActivity);
        ImageView forkknifeImage = (ImageView) convertView.findViewById(R.id.imageview_forkknife);
        ImageView nextArrow = (ImageView) convertView.findViewById(R.id.detailsarrow_imageview);

        // Populate the data into the template view using the data object
        name.setText("name data");
        distance.setText("Distance data");
        openStatus.setText("OPEN");

        // Return the completed view to render on screen
        return convertView;
    }
}
