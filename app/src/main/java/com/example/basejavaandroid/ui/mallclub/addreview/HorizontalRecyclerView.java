package com.example.basejavaandroid.ui.mallclub.addreview;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.basejavaandroid.R;

import java.util.ArrayList;

public class HorizontalRecyclerView extends RecyclerView.Adapter<HorizontalRecyclerView.HorizontalViewHolder> {
    private final ArrayList<Uri> uri;

    public HorizontalRecyclerView(ArrayList<Uri> uri) {
        this.uri = uri;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder horizontalViewHolder, int position) {
        Glide.with(horizontalViewHolder.mImageRecyclerView)
                .load(uri.get(position))
                .apply(new RequestOptions().override(300, 300))
                .into(horizontalViewHolder.mImageRecyclerView);
        /*Picasso.get()
                .load(uri.get(position))
                .into(horizontalViewHolder.mImageRecyclerView);*/
    }

    @Override
    public int getItemCount() {
        return uri.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageRecyclerView;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            mImageRecyclerView = itemView.findViewById(R.id.ivImage);
        }
    }
}
