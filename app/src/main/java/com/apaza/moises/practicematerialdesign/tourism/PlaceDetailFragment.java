package com.apaza.moises.practicematerialdesign.tourism;


import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apaza.moises.practicematerialdesign.R;
import com.apaza.moises.practicematerialdesign.model.PlaceContract;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaceDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceDetailFragment extends Fragment {
    private static final String ID = "id";

    private Uri uri;
    private long id;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getLong(ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place_detail, container, false);
    }

    private void loadViewWithPlace(long id){
        uri = ContentUris.withAppendedId(PlaceContract.CONTENT_URI, id);
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);

        if(!cursor.moveToFirst())
            return;
        int image = cursor.getInt(cursor.getColumnIndex(PlaceContract.Columns.IMAGE));
        String name = cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.NAME));
        String description = cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.DESCRIPTION));
        String address = cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.ADDRESS));

        /*ivImagePlace.setImageResource(image);
        etName.setText(name);
        etDescription.setText(description);
        etAddress.setText(address);*/
        cursor.close();
    }
}
