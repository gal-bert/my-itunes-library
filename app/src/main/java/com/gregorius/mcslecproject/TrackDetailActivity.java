package com.gregorius.mcslecproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class TrackDetailActivity extends AppCompatActivity
{
    protected static final String KEY_SENDER_ACTIVITY = "senderActivity";
    protected static final String KEY_TRACK = "track";
    protected static final int REQUEST_CODE_BOOKMARK = 1;


    Button buttonAction;
    ImageView imageViewArtwork;
    TextView textViewTrackName, textViewArtistName, textViewAlbumName, textViewType, textViewPrice;
    MediaResponse media;
    String callerActivity;
    int bookmarksId;
    BookmarksDetailDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_detail);

        buttonAction = findViewById(R.id.button_action);
        imageViewArtwork = findViewById(R.id.image_view_artwork);
        textViewTrackName = findViewById(R.id.text_view_track_name);
        textViewArtistName = findViewById(R.id.text_view_artist_name);
        textViewAlbumName = findViewById(R.id.text_view_album_name);
        textViewType = findViewById(R.id.text_view_type);
        textViewPrice = findViewById(R.id.text_view_price);

        Intent intent = getIntent();

        media = (MediaResponse) intent.getSerializableExtra(KEY_TRACK);
        if (media != null)
        {
            textViewTrackName.setText(media.getTrackName());
            textViewArtistName.setText(media.getArtistName());
            textViewAlbumName.setText(media.getCollectionName());
            textViewType.setText(media.getKind());
            textViewPrice.setText("$" + media.getTrackPrice());
            Glide.with(TrackDetailActivity.this).load(media.getArtworkUrl100()).into(imageViewArtwork);
        }

        callerActivity = intent.getStringExtra(BookmarksActivity.KEY_SENDER_ACTIVITY);
        if(callerActivity != null)
        {
            if (callerActivity.contentEquals(MainActivity.class.getSimpleName()))
            {
                buttonAction.setText("Add to Bookmark");
                buttonAction.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(TrackDetailActivity.this, BookmarksActivity.class);
                        intent.putExtra(BookmarksActivity.KEY_SENDER_ACTIVITY, TrackDetailActivity.this.getClass().getSimpleName());
                        startActivityForResult(intent, REQUEST_CODE_BOOKMARK);
                    }
                });
            }
            else if (callerActivity.contentEquals(BookmarkDetailActivity.class.getSimpleName()))
            {
                // from BookmarkDetailActivity. Please specify the class in the else by changing it to else if.
                buttonAction.setText("Remove from Bookmark");
                buttonAction.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        MediaResponsesDB mediaResponsesDB = new MediaResponsesDB(TrackDetailActivity.this);
                        int trackId = media.getTrackId();
                        Intent intent1 = getIntent();
                        int bookmarkId = intent1.getIntExtra(BookmarksActivity.KEY_BOOKMARK_ID,0);
                        db = new BookmarksDetailDB(TrackDetailActivity.this);
                        db.deleteBookmarkDetail(bookmarkId, trackId);
                        if(!db.isTrackInAnyBookmark(trackId)) mediaResponsesDB.deleteMediaResponses(trackId);
                        Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }
        }
    }

    public void viewOnItunes(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(media.getTrackViewUrl()));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_BOOKMARK)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                if(data != null)
                {
                    MediaResponsesDB mediaResponsesDB = new MediaResponsesDB(this);
                    BookmarksDetailDB bookmarksDetailDB = new BookmarksDetailDB(this);

                    bookmarksId = data.getIntExtra(BookmarksActivity.KEY_BOOKMARK_ID, 0);

                    if(mediaResponsesDB.getMediaResponses(media.getTrackId()) == null) mediaResponsesDB.insertMediaResponses(media);
                    if(bookmarksDetailDB.isBookmarksDetailOnTheTable(bookmarksId, media.getTrackId())) Toast.makeText(this, "Track has already added the bookmark", Toast.LENGTH_SHORT).show();
                    else
                    {
                        bookmarksDetailDB.insertBookmarksDetail(new BookmarksDetail(bookmarksId, media.getTrackId()));
                        Toast.makeText(this, "Successfully added.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}