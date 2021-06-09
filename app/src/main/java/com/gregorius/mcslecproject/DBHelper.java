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

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOKMARKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_BOOKMARKS);
        onCreate(db);
    }
}
