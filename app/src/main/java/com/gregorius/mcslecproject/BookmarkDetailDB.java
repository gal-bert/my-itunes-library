package com.gregorius.mcslecproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class BookmarkDetailDB {
    public DBHelper dbHelper;

    public BookmarkDetailDB(Context ctx){
        dbHelper = new DBHelper(ctx);
    }

    public void insertBookmarkDetail(BookmarkDetail bookmarkDetail){

        SQLiteDatabase db =dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_BOOKMARK_DETAIL_TITLE, bookmarkDetail.getDetailTitle());
        cv.put(DBHelper.FIELD_BOOKMARK_DETAIL_IMAGE, bookmarkDetail.getDetailImage());
        cv.put(DBHelper.FIELD_BOOKMARK_DETAIL_ARTIST, bookmarkDetail.getDetailArtist());
        db.insert(DBHelper.TABLE_BOOKMARK_DETAIL, null, cv);

        db.close();
    }

    public Vector<BookmarkDetail> getBookmarkDetail(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String get_product_query = "SELECT * FROM " + DBHelper.TABLE_BOOKMARK_DETAIL;
        Cursor cursor = db.rawQuery(get_product_query, null);

        Vector<BookmarkDetail> vecBookmarkDetail = new Vector<>();

        if(cursor.moveToFirst()){
            do{
                Integer id = cursor.getInt(0);
                String title = cursor.getString(1);
                String image = cursor.getString(2);
                String artist = cursor.getString(3);
                BookmarkDetail bookmarkDetail = new BookmarkDetail(id, title, image, artist);
                vecBookmarkDetail.add(bookmarkDetail);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        dbHelper.close();
        return vecBookmarkDetail;
    }
}
