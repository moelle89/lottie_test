package com.moelle.deepdarkness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.ImgMovie.setImageResource(mData.get(i).getThumbnail());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private ImageView ImgMovie;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            ImgMovie = itemView.findViewById(R.id.item_wall_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    wallItemClickListener.onMovieClick(mData.get(getAdapterPosition()), ImgMovie);


                }
            });

        }
    }
}
