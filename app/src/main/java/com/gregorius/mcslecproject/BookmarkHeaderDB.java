package com.gregorius.mcslecproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class BookmarkHeaderDB
{
    public DBHelper dbHelper;

    public BookmarkHeaderDB(Context ctx)
    {
        dbHelper = new DBHelper(ctx);
    }

    public void insertBookmarkHeader(BookmarksHeader bookmarksHeader)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_BOOKMARK_NAME, bookmarksHeader.getBookmarkName());
        db.insert(DBHelper.TABLE_BOOKMARKS_HEADER, null, cv);

        db.close();
    }

    public Vector<BookmarksHeader> getBookmarks()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Vector<BookmarksHeader> vecBookmark = new Vector<>();

        Cursor cursor = db.query(DBHelper.TABLE_BOOKMARKS_HEADER, null, null, null, null, null, null);

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_BOOKMARK_ID));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_BOOKMARK_NAME));

            BookmarksHeader bookmarksHeader = new BookmarksHeader(id, name);
            vecBookmark.add(bookmarksHeader);
        }
        cursor.close();
        db.close();
        dbHelper.close();
        return vecBookmark;
    }

    public void updateBookmark(int id, String name)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_BOOKMARK_NAME, name);

        String selection = DBHelper.FIELD_BOOKMARK_ID + "=?";
        String[] selectionArgs = {Integer.toString(id)};

        db.update(DBHelper.TABLE_BOOKMARKS_HEADER, cv, selection, selectionArgs);

        db.close();
        dbHelper.close();
    }

    public void deleteBookmark(int bookmarkId)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = DBHelper.FIELD_BOOKMARK_ID + "=?";
        String[] selectionArgs = {Integer.toString(bookmarkId)};

        db.delete(DBHelper.TABLE_BOOKMARKS_HEADER, selection, selectionArgs);

        db.close();
        dbHelper.close();
    }

}
