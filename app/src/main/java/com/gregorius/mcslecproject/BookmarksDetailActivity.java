package com.gregorius.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class BookmarksDetailActivity extends AppCompatActivity {

    TextView tvTitle;
    BookmarkDetail bookmarkDetail;
    BookmarkDetailDB db;
    RecyclerView rvBookmarkDetail;
    Vector<BookmarkDetail> vecBookmarkDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks_detail);

        db = new BookmarkDetailDB(this);
        int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        tvTitle = findViewById(R.id.tvTitle);
//        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
        tvTitle.setText(name + " Bookmark");

        bookmarkDetail = new BookmarkDetail("iTunes Store", "http://is4.mzstatic.com/image/thumb/Music122/v4/1b/83/76/1b8376aa-90a9-eae1-662d-2b24faf1bf6e/source/200x200bb.png", "The New York Times", 1);
        db.insertBookmarkDetail(bookmarkDetail);
        bookmarkDetail = new BookmarkDetail("The Daily", "http://is5.mzstatic.com/image/thumb/Music127/v4/21/22/5e/21225e89-71df-0f12-a8ee-33f66cfde476/source/200x200bb.png", "Mathis Entertainment, Inc.", 1);
        db.insertBookmarkDetail(bookmarkDetail);
        bookmarkDetail = new BookmarkDetail("Kickass News", "http://is5.mzstatic.com/image/thumb/Music128/v4/d5/c6/50/d5c65035-505e-b006-48e5-be3f0f8f19f8/source/200x200bb.png", "TED", 2);
        db.insertBookmarkDetail(bookmarkDetail);

        getRvData();


    }

    public void getRvData(){
        rvBookmarkDetail = findViewById(R.id.rvBookmarkDetail);
        db = new BookmarkDetailDB(this);
        vecBookmarkDetail = db.getBookmarkDetail();

        BookmarkDetailAdapter bookmarkDetailAdapter = new BookmarkDetailAdapter(this);
        bookmarkDetailAdapter.setVecBookmarkDetail(vecBookmarkDetail);

        rvBookmarkDetail.setAdapter(bookmarkDetailAdapter);
        rvBookmarkDetail.setLayoutManager(new LinearLayoutManager(this));
    }

}