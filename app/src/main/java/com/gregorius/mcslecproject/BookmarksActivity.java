package com.gregorius.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.Vector;

public class BookmarksActivity extends AppCompatActivity {

    Button btnAdd, btnAddName;
    EditText etBookmarkName;
    BookmarksHeader bookmarksHeader;
    BookmarkHeaderDB db;
    RecyclerView rvBookmark;
    Vector<BookmarksHeader> vecBookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        //RV BOOKMARK
        getRvData();
        //ADD BOOKMARK HEADER
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.popup_add_bookmark, null, false),650, 300, true);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                View view = popupWindow.getContentView();
                btnAddName = view.findViewById(R.id.btnName);
                etBookmarkName = view.findViewById(R.id.etBookmarkName);
                db = new BookmarkHeaderDB(getApplicationContext());

                btnAddName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();
                        String name = etBookmarkName.getText().toString();
                        bookmarksHeader = new BookmarksHeader(name);
                        db.insertBookmarkHeader(bookmarksHeader);
                        popupWindow.dismiss();
                        refresh();
                    }
                });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getRvData();
    }

    public void getRvData(){
        rvBookmark = findViewById(R.id.rvBookmarks);
        db = new BookmarkHeaderDB(this);
        vecBookmark = db.getBookmarks();

        BookmarkHeaderAdapter bookmarkHeaderAdapter = new BookmarkHeaderAdapter(this);
        bookmarkHeaderAdapter.setVecBookmark(vecBookmark);

        rvBookmark.setAdapter(bookmarkHeaderAdapter);
        bookmarkHeaderAdapter.notifyDataSetChanged();
        rvBookmark.setLayoutManager(new LinearLayoutManager(this));
    }

    public void refresh(){
        finish();
        startActivity(getIntent());
        overridePendingTransition(0,0);
    }

}