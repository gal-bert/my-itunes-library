package com.gregorius.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class BookmarksActivity extends AppCompatActivity
{
    protected static final String KEY_SENDER_ACTIVITY = "senderActivity";
    protected static final String KEY_BOOKMARK_ID = "bookmarkId";
    protected static final String KEY_BOOKMARK_NAME = "bookmarkName";

    Button btnAddName;
    ImageButton btnAdd;
    EditText etBookmarkName;
    BookmarksHeader bookmarksHeader;
    BookmarkHeaderDB db;
    RecyclerView rvBookmark;
    Vector<BookmarksHeader> vecBookmark;
    String callerActivity;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        Intent intent = getIntent();
        callerActivity = intent.getStringExtra(KEY_SENDER_ACTIVITY);

        tvTitle = findViewById(R.id.tvTitle);

        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String nickname = sPrefs.getString(NameActivity.KEY_NICKNAME, "My");

        tvTitle.setText(nickname + "'s Bookmarks");

        //ADD BOOKMARK HEADER
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.popup_add_bookmark, null, false), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                View view = popupWindow.getContentView();
                btnAddName = view.findViewById(R.id.btnName);
                etBookmarkName = view.findViewById(R.id.etBookmarkName);
                db = new BookmarkHeaderDB(getApplicationContext());

                btnAddName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Bookmark Created",Toast.LENGTH_SHORT).show();
                        String name = etBookmarkName.getText().toString();
                        bookmarksHeader = new BookmarksHeader(name);
                        db.insertBookmarkHeader(bookmarksHeader);
                        popupWindow.dismiss();
//                        refresh();
                        getRvData();
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

        bookmarkHeaderAdapter.setOnItemClickListener(new BookmarkHeaderAdapter.ClickListener()
        {
            @Override
            public void onItemClick(int position, View v)
            {
                int bookmarkId = bookmarkHeaderAdapter.getBookmarkVector().get(position).getBookmarkId();
                String bookmarkName = bookmarkHeaderAdapter.getBookmarkVector().get(position).getBookmarkName();
                if(callerActivity.contentEquals(MainActivity.class.getSimpleName()))
                {
                    // intent to BookmarkDetailActivity.
//                    Toast.makeText(BookmarksActivity.this, Integer.toString(bookmarkId), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), BookmarkDetailActivity.class);
                    intent.putExtra(KEY_BOOKMARK_ID, bookmarkId);
                    intent.putExtra(KEY_BOOKMARK_NAME, bookmarkName);
                    startActivity(intent);

                }
                else if(callerActivity.contentEquals(TrackDetailActivity.class.getSimpleName()))
                {
                    Intent intent = new Intent();
                    intent.putExtra(KEY_BOOKMARK_ID, bookmarkId);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}