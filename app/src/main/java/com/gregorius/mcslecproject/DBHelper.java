package com.gregorius.mcslecproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MediaDB";
    public static final int DB_VERSION = 1;

    public static final String TABLE_BOOKMARKS = "Bookmarks_header";
    public static final String FIELD_BOOKMARK_ID = "id";
    public static final String FIELD_BOOKMARK_NAME = "bookmark_name";

    public static final String CREATE_BOOKMARKS = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKMARKS +" (" +
            FIELD_BOOKMARK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_BOOKMARK_NAME + " TEXT)";

    public static final String DROP_BOOKMARKS = "DROP TABLE IF EXISTS " + TABLE_BOOKMARKS;

    public static final String TABLE_BOOKMARK_DETAIL = "Bookmarks_detail";
    public static final String FIELD_BOOKMARK_DETAIL_ID = "detail_id";
    public static final String FIELD_BOOKMARK_DETAIL_TITLE = "detail_title";
    public static final String FIELD_BOOKMARK_DETAIL_IMAGE = "detail_image";
    public static final String FIELD_BOOKMARK_DETAIL_ARTIST = "detail_artist";
    public static final String FIELD_BOOKMARK_DETAIL_F_KEY = "bookmark_id";

    public static final String CREATE_BOOKMARK_DETAIL = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKMARK_DETAIL +" (" +
            FIELD_BOOKMARK_DETAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_BOOKMARK_DETAIL_TITLE + " TEXT," +
            FIELD_BOOKMARK_DETAIL_IMAGE + " TEXT," +
            FIELD_BOOKMARK_DETAIL_ARTIST + " TEXT," +
            FIELD_BOOKMARK_DETAIL_F_KEY + " INTEGER, " +
            " FOREIGN KEY(" + FIELD_BOOKMARK_DETAIL_F_KEY + ") REFERENCES " + TABLE_BOOKMARKS + "(" + FIELD_BOOKMARK_ID + "))";

    public static final String DROP_BOOKMARK_DETAIL = "DROP TABLE IF EXISTS " + TABLE_BOOKMARK_DETAIL;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL(CREATE_BOOKMARKS);
        db.execSQL(CREATE_BOOKMARK_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_BOOKMARKS);
        db.execSQL(DROP_BOOKMARK_DETAIL);
        onCreate(db);
    }
}
