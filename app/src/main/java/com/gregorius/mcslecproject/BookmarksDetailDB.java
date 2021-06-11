package com.gregorius.mcslecproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class BookmarksDetailDB
{
    private DBHelper databaseHelper;

    public BookmarksDetailDB(Context context)
    {
        databaseHelper = new DBHelper(context);
    }

    public void insertBookmarksDetail(BookmarksDetail bookmarksDetail)
    {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.FIELD_BOOKMARK_ID, bookmarksDetail.getBookmarkId());
        contentValues.put(DBHelper.FIELD_TRACK_ID, bookmarksDetail.getTrackId());

        sqLiteDatabase.insert(DBHelper.TABLE_BOOKMARKS_DETAIL, null, contentValues);

        sqLiteDatabase.close();
    }

    public Vector<BookmarksDetail> getBookmarksDetail(int bookmarkId)
    {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Vector<BookmarksDetail> bookmarksDetailVector = new Vector<>();

        String selection = DBHelper.FIELD_BOOKMARK_ID + "=?";
        String[] selectionArgs = {Integer.toString(bookmarkId)};

        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_BOOKMARKS_DETAIL, null, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext())
        {
            int trackID = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_TRACK_ID));
            bookmarksDetailVector.add(new BookmarksDetail(bookmarkId, trackID));
        }

        cursor.close();
        sqLiteDatabase.close();
        databaseHelper.close();

        return bookmarksDetailVector;
    }

    public void deleteBookmarkDetail(int bookmarkId, int trackId)
    {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String selection = DBHelper.FIELD_BOOKMARK_ID + "=? AND " + DBHelper.FIELD_TRACK_ID + "=?";
        String[] selectionArgs = {Integer.toString(bookmarkId), Integer.toString(trackId)};

        sqLiteDatabase.delete(DBHelper.TABLE_BOOKMARKS_DETAIL, selection, selectionArgs);

        sqLiteDatabase.close();
        databaseHelper.close();
    }
}
