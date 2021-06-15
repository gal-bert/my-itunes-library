package com.gregorius.mcslecproject;

import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class BookmarkHeaderAdapter extends RecyclerView.Adapter<BookmarkHeaderAdapter.ViewHolder>
{
    Context ctx;
    Vector<BookmarksHeader> vecBookmark;
    BookmarkHeaderDB db;
    private ClickListener clickListener;

    public BookmarkHeaderAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public void setVecBookmark(Vector<BookmarksHeader> vecBookmark) {
        this.vecBookmark = vecBookmark;
    }

    public Vector<BookmarksHeader> getBookmarkVector()
    {
        return vecBookmark;
    }

    @NonNull
    @Override
    public BookmarkHeaderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_bookmark_header, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookmarkHeaderAdapter.ViewHolder holder, int position) {
        holder.tvBookmarkTitle.setText("" + vecBookmark.get(position).getBookmarkName());

        int id = vecBookmark.get(position).getBookmarkId();
        db = new BookmarkHeaderDB(ctx);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteBookmark(id);
                Toast.makeText(ctx,"Bookmark removed",Toast.LENGTH_LONG).show();
                vecBookmark.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, vecBookmark.size());
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {

            Button btnEditName;
            EditText etBookmarkName;
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.popup_add_bookmark, null, false),ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                View view = popupWindow.getContentView();
                btnEditName = view.findViewById(R.id.btnName);
                etBookmarkName = view.findViewById(R.id.etBookmarkName);
                btnEditName.setText("Update");
                etBookmarkName.setText("" + vecBookmark.get(position).getBookmarkName());
                etBookmarkName = view.findViewById(R.id.etBookmarkName);

                btnEditName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ctx,"Bookmark Updated",Toast.LENGTH_LONG).show();
                        String name = etBookmarkName.getText().toString();
                        db.updateBookmark(id, name);
                        vecBookmark.get(position).setBookmarkName(name);
                        popupWindow.dismiss();
                        notifyItemChanged(position);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return vecBookmark.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView tvBookmarkTitle;
        ImageButton btnDelete, btnEdit;
        CardView cvBookmarks;
        public ViewHolder(View itemView) {
            super(itemView);
            tvBookmarkTitle = itemView.findViewById(R.id.tvBookmarkTitle);
            cvBookmarks = itemView.findViewById(R.id.cvBookmarks);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            clickListener.onItemClick(getBindingAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
