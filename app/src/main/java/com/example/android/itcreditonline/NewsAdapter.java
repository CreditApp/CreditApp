package com.example.android.itcreditonline;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.itcreditonline.Model.FeedItem;

import java.util.ArrayList;

/**
 * Created by Simeon Angelov on 1.10.2016 г..
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{
    private ArrayList<FeedItem> feedItems;
    Context activity;

    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(activity).inflate(R.layout.adapter_news,parent,false);
        return new MyViewHolder(row);
    }

    public NewsAdapter(ArrayList<FeedItem> feedItems, Context activity) {
        this.feedItems = feedItems;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.MyViewHolder holder, int position) {
        FeedItem f = feedItems.get(position);
        holder.title.setText(f.getTitle());
        holder.pubDate.setText(f.getPubDate());
        holder.description.setText(f.getDescription());
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView pubDate;
        TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleTV);
            pubDate = (TextView) itemView.findViewById(R.id.pubDateTV);
            description = (TextView) itemView.findViewById(R.id.descriptionTV);
        }
    }
}
