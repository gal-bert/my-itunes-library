package com.gregorius.mcslecproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class BookmarkHeaderDB {
    public DBHelper dbHelper;

    public BookmarkHeaderDB(Context ctx){
        dbHelper = new DBHelper(ctx);
    }

    public void insertBookmarkHeader(BookmarksHeader bookmarksHeader){

        SQLiteDatabase db =dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_BOOKMARK_NAME, bookmarksHeader.getBookmarkName());
        db.insert(DBHelper.TABLE_BOOKMARKS, null, cv);

        db.close();
    }

    public Vector<BookmarksHeader> getBookmarks(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String get_product_query = "SELECT * FROM " + DBHelper.TABLE_BOOKMARKS;
        Cursor cursor = db.rawQuery(get_product_query, null);

        Vector<BookmarksHeader> vecBookmark = new Vector<>();

        if(cursor.moveToFirst()){
            do{
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                BookmarksHeader bookmarksHeader = new BookmarksHeader(id, name);
                vecBookmark.add(bookmarksHeader);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        dbHelper.close();
        return  vecBookmark;
    }

    public void updateBookmark(int id, String name){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String update_query = "UPDATE " + DBHelper.TABLE_BOOKMARKS + " SET " +
                DBHelper.FIELD_BOOKMARK_NAME + " = '" + name + "' " +
                "WHERE " + DBHelper.FIELD_BOOKMARK_ID + " = " + id;
        db.execSQL(update_query);
        db.close();
        dbHelper.close();
    }

    public void deleteBookmark(int bookmarkId){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String delete_query = "DELETE FROM " + DBHelper.TABLE_BOOKMARKS +
                " WHERE " + DBHelper.FIELD_BOOKMARK_ID + " = " + bookmarkId;
        db.execSQL(delete_query);
        db.close();
        dbHelper.close();
    }



}
