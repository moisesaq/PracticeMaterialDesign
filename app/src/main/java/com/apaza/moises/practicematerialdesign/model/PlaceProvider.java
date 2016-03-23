package com.apaza.moises.practicematerialdesign.model;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class PlaceProvider extends ContentProvider{

    private static final String DATA_BASE_NAME = "DBPlace";
    private static final int DB_VERSION = 1;

    private DataBaseHelper dataBaseHelper;
    @Override
    public boolean onCreate() {
        dataBaseHelper = new DataBaseHelper(getContext(), DATA_BASE_NAME, null, DB_VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        int match = PlaceContract.uriMatcher.match(uri);

        Cursor cursor;

        switch (match){
            case PlaceContract.ALL_ROWS:
                cursor = db.query(PlaceContract.PLACE, projection,
                        selection, selectionArgs,
                        null, null, sortOrder);
                cursor.setNotificationUri(
                        getContext().getContentResolver(),
                        PlaceContract.CONTENT_URI);
                break;
            case PlaceContract.SINGLE_ROWS:
                long idPlace = ContentUris.parseId(uri);
                cursor = db.query(PlaceContract.PLACE, projection,
                        PlaceContract.Columns._ID + " = " + idPlace,
                        selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(
                        getContext().getContentResolver(),
                        PlaceContract.CONTENT_URI);
                break;
            default:
                throw new IllegalArgumentException("Uri no support" + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
