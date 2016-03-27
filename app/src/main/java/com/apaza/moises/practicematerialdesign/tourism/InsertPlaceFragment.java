package com.apaza.moises.practicematerialdesign.tourism;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.apaza.moises.practicematerialdesign.R;
import com.apaza.moises.practicematerialdesign.model.PlaceContract;

public class InsertPlaceFragment extends Fragment {

    private View view;
    private EditText etName, etDescription, etAddress;

    public InsertPlaceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActivity().getActionBar().setHomeAsUpIndicator(R.drawable.ic_check_white_24dp);
        setHasOptionsMenu(true);
        //getActivity().getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check_white_24dp);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_insert_place, container, false);
        setup();
        return view;
    }

    private void setup(){
        etName = (EditText)view.findViewById(R.id.etName);
        etDescription = (EditText)view.findViewById(R.id.etDescription);
        etAddress = (EditText)view.findViewById(R.id.etAddress);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_save){
            saveData();
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveData(){
        try{
            ContentValues values = new ContentValues();
            values.put(PlaceContract.Columns.NAME, etName.getText().toString());
            values.put(PlaceContract.Columns.DESCRIPTION, etDescription.getText().toString());
            values.put(PlaceContract.Columns.ADDRESS, etAddress.getText().toString());
            values.put(PlaceContract.Columns.RATING, 2);
            values.put(PlaceContract.Columns.IMAGE, R.drawable.museum);
            getActivity().getContentResolver().insert(PlaceContract.CONTENT_URI, values);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_save).setVisible(true);
    }
}
