package com.gregorius.mcslecproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Vector;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    protected Context context;
    private Vector<MediaResponse> listMedia;

    public MediaAdapter(Context context) {
        this.context = context;
        listMedia = new Vector<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row_media, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaAdapter.ViewHolder holder, int position) {
        MediaResponse media = getListMedia().get(position);
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
                context.startActivity(intent);
//                Toast.makeText(context,"" + media.getTrackName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMedia.size();
    }

    public Vector<MediaResponse> getListMedia() {
        return listMedia;
    }

    public void setListMedia(Vector<MediaResponse> listMedia) {
        this.listMedia = listMedia;
        notifyDataSetChanged();
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
