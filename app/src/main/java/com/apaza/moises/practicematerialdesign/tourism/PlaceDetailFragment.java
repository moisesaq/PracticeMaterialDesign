package com.apaza.moises.practicematerialdesign.tourism;


import android.app.Activity;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.apaza.moises.practicematerialdesign.R;
import com.apaza.moises.practicematerialdesign.model.PlaceContract;

public class PlaceDetailFragment extends Fragment {
    private static final String ID = "id";

    private ImageView ivImagePlace;
    private RatingBar ratingBar;
    private TextView tvName, tvAddress, tvDescription;
    private View view;

    private long idPlace;
    private Uri uriPlace;

    OnDetailPlaceClickListener mListener;

    public static PlaceDetailFragment newInstance(long id) {
        PlaceDetailFragment fragment = new PlaceDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_place_detail, container, false);
        setHasOptionsMenu(true);
        setup();
        return view;
    }

    private void setup(){
        ivImagePlace = (ImageView)view.findViewById(R.id.ivImagePlace);
        ratingBar = (RatingBar)view.findViewById(R.id.ratingBar);
        tvName = (TextView)view.findViewById(R.id.tvName);
        tvAddress = (TextView)view.findViewById(R.id.tvAddress);
        tvDescription = (TextView)view.findViewById(R.id.tvDescription);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            idPlace = getArguments().getLong(ID);
            loadViewWithPlace(idPlace);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_edit).setVisible(true);
        menu.findItem(R.id.action_delete).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;

            case R.id.action_edit:
                mListener.onEditPlaceClick(idPlace);
                return true;

            case R.id.action_delete:
                deletePlace();
                getActivity().onBackPressed();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    private void loadViewWithPlace(long id){
        uriPlace = ContentUris.withAppendedId(PlaceContract.CONTENT_URI, id);
        Cursor cursor = getActivity().getContentResolver().query(uriPlace, null, null, null, null);

        if(!cursor.moveToFirst())
            return;
        int image = cursor.getInt(cursor.getColumnIndex(PlaceContract.Columns.IMAGE));
        String name = cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.NAME));
        String description = cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.DESCRIPTION));
        String address = cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.ADDRESS));
        float rating = (float) cursor.getDouble(cursor.getColumnIndex(PlaceContract.Columns.RATING));

        ivImagePlace.setImageResource(image);
        ratingBar.setRating(rating);
        tvName.setText(name);
        tvDescription.setText(description);
        tvAddress.setText(address);
        cursor.close();
    }

    public interface OnDetailPlaceClickListener {
        void onEditPlaceClick(long id);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnDetailPlaceClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnDetailPlaceClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void deletePlace(){
        getActivity().getContentResolver().delete(uriPlace, null, null);
    }
}
