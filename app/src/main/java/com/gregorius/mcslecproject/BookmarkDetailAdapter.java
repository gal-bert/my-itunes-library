package com.gregorius.mcslecproject;

import android.content.Context;
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

import java.util.Vector;

public class BookmarkDetailAdapter extends RecyclerView.Adapter<BookmarkDetailAdapter.ViewHolder> {

    Context ctx;
    Vector<BookmarkDetail> vecBookmarkDetail;
    BookmarkDetailDB db;

    public BookmarkDetailAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public void setVecBookmarkDetail(Vector<BookmarkDetail> vecBookmarkDetail) {
        this.vecBookmarkDetail = vecBookmarkDetail;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookmarkDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_bookmark_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkDetailAdapter.ViewHolder holder, int position) {
        BookmarkDetail bookmarkDetail = vecBookmarkDetail.get(position);
        Glide.with(ctx).load(bookmarkDetail.getDetailImage()).into(holder.artworkUrl100);
        holder.trackName.setText(bookmarkDetail.getDetailTitle());
        holder.artistName.setText(bookmarkDetail.getDetailArtist());

        holder.cvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"" + bookmarkDetail.getDetailTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return vecBookmarkDetail.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView artworkUrl100;
        TextView trackName;
        TextView artistName;
        CardView cvDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            artworkUrl100 = itemView.findViewById(R.id.artworkUrl100);
            trackName = itemView.findViewById(R.id.trackName);
            artistName = itemView.findViewById(R.id.artistName);
            cvDetail = itemView.findViewById(R.id.cvDetail);
        }
    }
}
