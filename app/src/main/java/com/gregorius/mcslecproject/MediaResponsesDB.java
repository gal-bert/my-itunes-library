package com.gregorius.mcslecproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class MediaResponsesDB
{
    private DBHelper databaseHelper;

    public MediaResponsesDB(Context context)
    {
        databaseHelper = new DBHelper(context);
    }

    public void insertMediaResponses(MediaResponse mediaResponse)
    {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.FIELD_TRACK_ID, mediaResponse.getTrackId());
        contentValues.put(DBHelper.FIELD_TRACK_NAME, mediaResponse.getTrackName());
        contentValues.put(DBHelper.FIELD_TRACK_ARTWORK_URL, mediaResponse.getArtworkUrl100());
        contentValues.put(DBHelper.FIELD_ARTIST_NAME, mediaResponse.getArtistName());
        contentValues.put(DBHelper.FIELD_COLLECTION_NAME, mediaResponse.getCollectionName());
        contentValues.put(DBHelper.FIELD_TRACK_VIEW_URL, mediaResponse.getTrackViewUrl());
        contentValues.put(DBHelper.FIELD_TRACK_KIND, mediaResponse.getKind());
        contentValues.put(DBHelper.FIELD_TRACK_PRICE, mediaResponse.getTrackPrice());

        sqLiteDatabase.insert(DBHelper.TABLE_TRACK, null, contentValues);

        sqLiteDatabase.close();
    }

    public MediaResponse getMediaResponses(int trackId)
    {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        MediaResponse mediaResponse = null;

        String selection = DBHelper.FIELD_TRACK_ID + "=?";
        String[] selectionArgs = {Integer.toString(trackId)};

        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_TRACK, null, selection, selectionArgs, null, null, null);

        if (cursor.moveToNext())
        {
            String trackName = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_TRACK_NAME));
            String artworkUrl = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_TRACK_ARTWORK_URL));
            String artistName = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_ARTIST_NAME));
            String collectionName = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_COLLECTION_NAME));
            String trackViewUrl = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_TRACK_VIEW_URL));
            String kind = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_TRACK_KIND));
            double trackPrice = cursor.getDouble(cursor.getColumnIndex(DBHelper.FIELD_TRACK_PRICE));

            mediaResponse = new MediaResponse(trackId, artworkUrl, trackName, artistName, collectionName, trackViewUrl, kind, trackPrice);
        }

        cursor.close();
        sqLiteDatabase.close();
        databaseHelper.close();

        return mediaResponse;
    }

    public void deleteMediaResponses(int trackId)
    {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String selection = DBHelper.FIELD_TRACK_ID + "=?";
        String[] selectionArgs = {Integer.toString(trackId)};

        sqLiteDatabase.delete(DBHelper.TABLE_TRACK, selection, selectionArgs);

        sqLiteDatabase.close();
        databaseHelper.close();
    }
}
