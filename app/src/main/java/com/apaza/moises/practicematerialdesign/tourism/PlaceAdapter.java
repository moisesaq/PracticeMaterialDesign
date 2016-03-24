package com.apaza.moises.practicematerialdesign.tourism;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;


public class PlaceAdapter extends CursorAdapter {

    public PlaceAdapter(Context context){
        super(context, null, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
