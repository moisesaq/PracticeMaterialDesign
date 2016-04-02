package com.apaza.moises.practicematerialdesign;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.apaza.moises.practicematerialdesign.model.Place;
import com.bumptech.glide.Glide;

import java.util.List;

public class GridAdapter extends ArrayAdapter<Place> implements View.OnClickListener{

    protected Activity activity;
    public GridAdapter(Context context, List<Place> list){
        super(context, R.layout.grid_item, list);
        this.activity = (Activity)context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Place getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = activity.getLayoutInflater();//(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.grid_item, null);
            holder.image = (ImageView)view.findViewById(R.id.image);
            holder.name = (TextView)view.findViewById(R.id.name);
            holder.description = (TextView)view.findViewById(R.id.description);
            holder.address = (TextView)view.findViewById(R.id.address);
            holder.rating = (RatingBar)view.findViewById(R.id.ratingBar);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        Place place = this.getItem(position);
        //holder.image.setImageResource(place.getImage());
        Glide.with(getContext()).load(place.getImage()).into(holder.image);
        holder.name.setText(place.getName());
        holder.description.setText(place.getDescription());
        holder.address.setText(place.getAddress());
        holder.rating.setRating((float)place.getRating());
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder{
        ImageView image;
        TextView name;
        TextView description;
        TextView address;
        RatingBar rating;
    }
}
