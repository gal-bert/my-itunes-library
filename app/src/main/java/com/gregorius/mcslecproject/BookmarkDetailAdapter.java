package com.gregorius.mcslecproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Vector;

public class BookmarkDetailAdapter extends RecyclerView.Adapter<BookmarkDetailAdapter.ViewHolder> {

    Vector<BookmarksDetail> vecBookmarkDetail;
    Context context;
    MediaResponsesDB db;

    protected static final String KEY_BOOKMARK_ID = "bookmarkId";

    public void setVecBookmarkDetail(Vector<BookmarksDetail> vecBookmarkDetail) {
        this.vecBookmarkDetail = vecBookmarkDetail;
    }

    public BookmarkDetailAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BookmarkDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row_media, parent, false);
        return new BookmarkDetailAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkDetailAdapter.ViewHolder holder, int position) {
        int trackId = vecBookmarkDetail.get(position).getTrackId();
        db = new MediaResponsesDB(context);
        MediaResponse media = db.getMediaResponses(trackId);

        Glide.with(context).load(media.getArtworkUrl100()).into(holder.artworkUrl100);

        holder.trackId.setText(String.valueOf(media.getTrackId()));
        holder.trackName.setText(media.getTrackName());
        holder.artistName.setText(media.getArtistName());

        holder.cvMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrackDetailActivity.class);
                intent.putExtra(TrackDetailActivity.KEY_TRACK, media);
                intent.putExtra(TrackDetailActivity.KEY_SENDER_ACTIVITY, context.getClass().getSimpleName());
                intent.putExtra(KEY_BOOKMARK_ID, vecBookmarkDetail.get(position).getBookmarkId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vecBookmarkDetail.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView trackId;
        ImageView artworkUrl100;
        TextView trackName;
        TextView artistName;
        CardView cvMedia;

        public ViewHolder(View itemView) {
            super(itemView);

            trackId = itemView.findViewById(R.id.trackId);
            artworkUrl100 = itemView.findViewById(R.id.artworkUrl100);
            trackName = itemView.findViewById(R.id.trackName);
            artistName = itemView.findViewById(R.id.artistName);
            cvMedia = itemView.findViewById(R.id.cvMedia);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
