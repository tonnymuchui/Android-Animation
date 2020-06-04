package com.example.animation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> implements Filterable {

    Context mContext;
    List<NewsItem> mData;
    List<NewsItem> mDataFiltered;
    boolean isDark = false;

    public NewsAdapter(Context mContext, List<NewsItem> mData, boolean isDark) {
        this.mContext = mContext;
        this.mData = mData;
        this.isDark = isDark;
        this.mDataFiltered = mData;
    }

    public NewsAdapter(Context mContext, List<NewsItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
//        animation for image user
        holder.img_user.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_animation_transiction));
//animation for whole card
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));

        holder.tv_title.setText(mDataFiltered.get(position).getTitle());
        holder.tv_content.setText(mDataFiltered.get(position).getContent());
        holder.tv_date.setText(mDataFiltered.get(position).getDate());
        holder.img_user.setImageResource(mDataFiltered.get(position).getUserPhoto());
    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
               String key = constraint.toString();
               if (key.isEmpty()){
                   mDataFiltered = mData;
               }
               else {
                   List<NewsItem> isFiltered = new ArrayList<>();
                   for (NewsItem row: mData){
                       if (row.getTitle().toLowerCase().contains(key.toLowerCase())){
                           isFiltered.add(row);
                       }
                   }
                   mDataFiltered = isFiltered;
               }
               FilterResults filterResults = new FilterResults();
               filterResults.values = mDataFiltered;
               return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFiltered = (List<NewsItem>) results.values;
            }
        };
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title, tv_content, tv_date;
        ImageView img_user;
        RelativeLayout container;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_description);
            tv_date = itemView.findViewById(R.id.tv_date);
            img_user = itemView.findViewById(R.id.img_user);

            if (isDark){
                setDarkMode();
            }
        }
        public void setDarkMode(){
            container.setBackgroundResource(R.drawable.card_bg_dark);
        }
    }
}
