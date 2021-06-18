package com.gregorius.mcslecproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = "MediaDB";
    public static final int DB_VERSION = 1;

    public static final String TABLE_BOOKMARKS_HEADER = "BookmarksHeader";
    public static final String TABLE_BOOKMARKS_DETAIL = "BookmarksDetail";
    public static final String TABLE_TRACK = "Track";
    public static final String FIELD_BOOKMARK_ID = "BookmarkId";
    public static final String FIELD_BOOKMARK_NAME = "BookmarkName";
    public static final String FIELD_TRACK_ID = "TrackId";
    public static final String FIELD_TRACK_ARTWORK_URL = "TrackArtworkURL";
    public static final String FIELD_TRACK_NAME = "TrackName";
    public static final String FIELD_ARTIST_NAME = "ArtistName";
    public static final String FIELD_COLLECTION_NAME = "CollectionName";
    public static final String FIELD_TRACK_VIEW_URL = "TrackViewUrl";
    public static final String FIELD_TRACK_KIND = "TrackKind";
    public static final String FIELD_TRACK_PRICE = "Price";

    public static final String CREATE_BOOKMARKS_HEADER =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKMARKS_HEADER +
                    " (" +
                    FIELD_BOOKMARK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FIELD_BOOKMARK_NAME + " TEXT" +
                    " )";

    public static final String CREATE_BOOKMARKS_DETAIL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKMARKS_DETAIL +
                    " (" +
                    FIELD_BOOKMARK_ID + " INTEGER REFERENCES " + TABLE_BOOKMARKS_HEADER + "(" + FIELD_BOOKMARK_ID + ") ON UPDATE CASCADE ON DELETE CASCADE," +
                    FIELD_TRACK_ID + " INTEGER REFERENCES " + TABLE_TRACK + "(" + FIELD_TRACK_ID + ") ON UPDATE CASCADE ON DELETE CASCADE," +
                    "PRIMARY KEY(" + FIELD_BOOKMARK_ID + ", " + FIELD_TRACK_ID + ")" +
                    " )";

    public static final String CREATE_TRACK =
            "CREATE TABLE IF NOT EXISTS " + TABLE_TRACK +
                    " (" +
                    FIELD_TRACK_ID + " INTEGER PRIMARY KEY," +
                    FIELD_TRACK_NAME + " TEXT," +
                    FIELD_TRACK_ARTWORK_URL + " TEXT," +
                    FIELD_ARTIST_NAME + " TEXT," +
                    FIELD_COLLECTION_NAME + " TEXT," +
                    FIELD_TRACK_VIEW_URL + " TEXT," +
                    FIELD_TRACK_KIND + " TEXT," +
                    FIELD_TRACK_PRICE + " REAL" +
                    " )";


    public static final String DROP_BOOKMARKS_HEADER = "DROP TABLE IF EXISTS " + TABLE_BOOKMARKS_HEADER;

    public static final String DROP_BOOKMARKS_DETAIL = "DROP TABLE IF EXISTS " + TABLE_BOOKMARKS_DETAIL;

    public static final String DROP_TRACK = "DROP TABLE IF EXISTS " + TABLE_TRACK;

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_BOOKMARKS_HEADER);
        db.execSQL(CREATE_BOOKMARKS_DETAIL);
        db.execSQL(CREATE_TRACK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_BOOKMARKS_HEADER);
        db.execSQL(DROP_BOOKMARKS_DETAIL);
        db.execSQL(DROP_TRACK);
        onCreate(db);
    }
}
