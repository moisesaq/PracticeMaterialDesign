package com.apaza.moises.practicematerialdesign.model;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

public class PlaceContract {

    public final static String AUTHORITY = "com.apaza.moises.practicematerialdesign.model.PlaceContract";

    public final static String PLACE = "place";

    public final static String SINGLE_MIME = "vnd.android.cursor.item/vnd." + AUTHORITY + PLACE;

    public final static String MULTIPLE_MIME = "vnd.android.cursor.dir/vnd." + AUTHORITY + PLACE;

    public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PLACE);

    public final static int ALL_ROWS = 1;

    public final static int SINGLE_ROWS = 2;

    public final static UriMatcher uriMatcher;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PLACE, ALL_ROWS);
        uriMatcher.addURI(AUTHORITY, PLACE + "/#", SINGLE_ROWS);
    }

    public static final  class Columns implements BaseColumns{

        private Columns(){

        }

        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String ADDRESS = "address";
        public static final String RATING = "rating";
        public static final String IMAGE = "image";
    }

}
