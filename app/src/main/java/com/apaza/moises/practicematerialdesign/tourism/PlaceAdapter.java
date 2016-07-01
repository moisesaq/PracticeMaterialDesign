package com.apaza.moises.practicematerialdesign.tourism;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.apaza.moises.practicematerialdesign.R;
import com.apaza.moises.practicematerialdesign.model.PlaceContract;
import com.bumptech.glide.Glide;


public class PlaceAdapter extends CursorAdapter {

    private Context context;
    public PlaceAdapter(Context context){
        super(context, null, 0);
        this.context = context;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.grid_header, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView image = (ImageView)view.findViewById(R.id.image);
        loadImage(image, cursor.getInt(cursor.getColumnIndex(PlaceContract.Columns.IMAGE)));
        /*image.setImageResource(cursor.getInt(cursor.getColumnIndex(PlaceContract.Columns.IMAGE)));*/

        TextView name = (TextView)view.findViewById(R.id.name);
        name.setText(cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.NAME)));

        TextView description = (TextView)view.findViewById(R.id.description);
        description.setText(cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.DESCRIPTION)));

        TextView address = (TextView)view.findViewById(R.id.address);
        address.setText(cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.ADDRESS)));

        RatingBar  rating = (RatingBar)view.findViewById(R.id.ratingBar);
        rating.setRating((float)cursor.getDouble(cursor.getColumnIndex(PlaceContract.Columns.RATING)));
    }

    private void loadImage(ImageView imageView, int id) {
        Glide.with(context)
                .load(id)
                .centerCrop()
                .into(imageView);
    }
}
