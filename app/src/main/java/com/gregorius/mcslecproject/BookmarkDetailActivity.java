package com.gregorius.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Vector;

public class BookmarkDetailActivity extends AppCompatActivity {

    protected static final String KEY_BOOKMARK_ID = "bookmarkId";
    protected static final String KEY_BOOKMARK_NAME = "bookmarkName";

    Vector<BookmarksDetail> vecBookmarkDetail;
    RecyclerView rvBookmarkDetail;
    BookmarksDetailDB db;
    TextView tvBookmarkDetailTitle;

    int bookmarkId;
    String bookmarkName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_detail);

        tvBookmarkDetailTitle = findViewById(R.id.tvBookmarkDetailTitle);

        Intent intent = getIntent();
        bookmarkId =  intent.getIntExtra(KEY_BOOKMARK_ID,0);
        bookmarkName = intent.getStringExtra(KEY_BOOKMARK_NAME);

        tvBookmarkDetailTitle.setText(bookmarkName);

    }

    @Override
    protected void onResume() {
        super.onResume();
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