package com.apaza.moises.practicematerialdesign.tourism;

import android.content.ContentUris;
import android.content.ContentValues;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.apaza.moises.practicematerialdesign.R;
import com.apaza.moises.practicematerialdesign.model.Place;
import com.apaza.moises.practicematerialdesign.model.PlaceContract;

public class InsertUpdatePlaceFragment extends Fragment {

    public static final String ACTION = "action";
    public static final String ID = "id";
    public static final String INSERT_ACTION = "insert_action";
    public static final String UPDATE_ACTION = "update_action";

    private View view;
    private ImageView ivImagePlace;
    private EditText etName, etDescription, etAddress;
    private String action = "";
    private Uri updateUri;


    public static InsertUpdatePlaceFragment newInstance(String action, long id){
        InsertUpdatePlaceFragment insertUpdatePlaceFragment = new InsertUpdatePlaceFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ACTION, action);
        bundle.putLong(ID, id);
        insertUpdatePlaceFragment.setArguments(bundle);
        return insertUpdatePlaceFragment;
    }

    public InsertUpdatePlaceFragment() {
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
        view = inflater.inflate(R.layout.fragment_insert_update_place, container, false);
        setup();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        action = getArguments().getString(ACTION);
        if(action.equals(UPDATE_ACTION)){
            long id = getArguments().getLong(ID);
            if(id != -1)
                loadViewWithPlace(id);
        }
    }

    private void setup(){
        ivImagePlace = (ImageView)view.findViewById(R.id.ivImagePlace);
        etName = (EditText)view.findViewById(R.id.etName);
        etDescription = (EditText)view.findViewById(R.id.etDescription);
        etAddress = (EditText)view.findViewById(R.id.etAddress);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_save).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_save){
            if(!etName.getText().equals("") && !etDescription.getText().toString().equals("") && !etAddress.getText().toString().equals("")){
                if(action.equals(UPDATE_ACTION))
                    updatePlace();
                else
                    saveData();
                getActivity().onBackPressed();
            }else{
                showToast("Fill in all fields");
            }

            return true;
        }
        return false;
    }

    private void saveData(){
        try{
            ContentValues values = new ContentValues();
            Place p = getPlaceFromView();
            values.put(PlaceContract.Columns.NAME, p.getName());
            values.put(PlaceContract.Columns.DESCRIPTION, p.getDescription());
            values.put(PlaceContract.Columns.ADDRESS, p.getAddress());
            values.put(PlaceContract.Columns.RATING, p.getRating());
            values.put(PlaceContract.Columns.IMAGE, p.getImage());
            getActivity().getContentResolver().insert(PlaceContract.CONTENT_URI, values);
            showToast("Saved place");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void updatePlace(){
        try{
            ContentValues values = new ContentValues();
            Place p = getPlaceFromView();
            values.put(PlaceContract.Columns.NAME, p.getName());
            values.put(PlaceContract.Columns.DESCRIPTION, p.getDescription());
            values.put(PlaceContract.Columns.ADDRESS, p.getAddress());
            values.put(PlaceContract.Columns.RATING, p.getRating());
            values.put(PlaceContract.Columns.IMAGE, p.getImage());
            getActivity().getContentResolver().update(updateUri, values, null, null);
            showToast("Updated place");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void loadViewWithPlace(long id){
        updateUri = ContentUris.withAppendedId(PlaceContract.CONTENT_URI, id);
        Cursor cursor = getActivity().getContentResolver().query(updateUri, null, null, null, null);

        if(!cursor.moveToFirst())
            return;
        int image = cursor.getInt(cursor.getColumnIndex(PlaceContract.Columns.IMAGE));
        String name = cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.NAME));
        String description = cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.DESCRIPTION));
        String address = cursor.getString(cursor.getColumnIndex(PlaceContract.Columns.ADDRESS));

        ivImagePlace.setImageResource(image);
        etName.setText(name);
        etDescription.setText(description);
        etAddress.setText(address);
        cursor.close();
    }

    public void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public Place getPlaceFromView(){
        Place place = new Place(etName.getText().toString(), etDescription.getText().toString(), etAddress.getText().toString(), 2, R.drawable.sucre);
        return place;
    }
}
