package com.apaza.moises.practicematerialdesign.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
        loadDataTest(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTable(SQLiteDatabase db){
        String table_place = "CREATE TABLE " + PlaceContract.PLACE + "(" +
                PlaceContract.Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PlaceContract.Columns.NAME + " TEXT, " +
                PlaceContract.Columns.DESCRIPTION + " TEXT, " +
                PlaceContract.Columns.ADDRESS + " TEXT, " +
                PlaceContract.Columns.RATING + " DOUBLE, " +
                PlaceContract.Columns.IMAGE + " INTEGER);";
        db.execSQL(table_place);
    }

    public void loadDataTest(SQLiteDatabase db){
        List<Place> list = Place.getListPlaces();
        for (Place place: list) {
            String query = "INSERT INTO " + PlaceContract.PLACE + "(" +
                    PlaceContract.Columns.NAME  + ", " + ", " + PlaceContract.Columns.DESCRIPTION + ", " + PlaceContract.Columns.ADDRESS + ", " +
                    PlaceContract.Columns.RATING + ", " + PlaceContract.Columns.IMAGE + ") " +
                    " VALUES ('" + place.getName() + "', '" + place.getDescription() + "', '" + place.getAddress() + "', "
                    + place.getRating() + ", " + place.getImage() + ")";
            db.execSQL(query);
        }
    }

}
