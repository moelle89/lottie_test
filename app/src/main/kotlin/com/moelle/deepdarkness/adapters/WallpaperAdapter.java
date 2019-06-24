package com.moelle.deepdarkness.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.moelle.deepdarkness.models.Wall;
import com.moelle.deepdarkness.R;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.MyViewHolder> {

    Context context;
    List<Wall> mData;
    WallItemClickListener wallItemClickListener;


    public WallpaperAdapter(Context context, List<Wall> mData, WallItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        wallItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wallpaper, viewGroup, false);
        return new MyViewHolder(view, wallItemClickListener, mData);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final String thumbnailUrl = mData.get(i).getThumbnailUrl();
        final View spinner = myViewHolder.itemView.findViewById(R.id.spinner);
        Glide.with(myViewHolder.itemView.getContext())
                .load(thumbnailUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        spinner.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        spinner.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(myViewHolder.ImgMovie);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ImgMovie;

        MyViewHolder(@NonNull View itemView, final WallItemClickListener listener,
                     final List<Wall> data) {
            super(itemView);
            ImgMovie = itemView.findViewById(R.id.item_wall_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMovieClick(data.get(getAdapterPosition()), ImgMovie);
                }
            });

        }
    }
}