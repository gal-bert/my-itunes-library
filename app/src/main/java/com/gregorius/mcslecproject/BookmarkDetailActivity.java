package com.gregorius.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.Vector;

public class BookmarkDetailActivity extends AppCompatActivity {

    protected static final String KEY_BOOKMARK_ID = "bookmarkId";

    Vector<BookmarksDetail> vecBookmarkDetail;
    RecyclerView rvBookmarkDetail;
    BookmarksDetailDB db;

    int bookmarkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_detail);
        Intent intent = getIntent();
        bookmarkId =  intent.getIntExtra(KEY_BOOKMARK_ID,0);
        getRvData();

    }

    public void getRvData(){
        rvBookmarkDetail = findViewById(R.id.rvBookmarkDetail);
        db = new BookmarksDetailDB(this);
        vecBookmarkDetail = db.getBookmarksDetail(bookmarkId);

        BookmarkDetailAdapter bookmarkDetailAdapter = new BookmarkDetailAdapter(this);
        bookmarkDetailAdapter.setVecBookmarkDetail(vecBookmarkDetail);

        rvBookmarkDetail.setAdapter(bookmarkDetailAdapter);
        rvBookmarkDetail.setLayoutManager(new LinearLayoutManager(this));
    }
}