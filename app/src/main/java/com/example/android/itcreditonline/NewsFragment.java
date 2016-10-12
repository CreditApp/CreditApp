package com.example.android.itcreditonline;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.itcreditonline.Model.FeedItem;
import com.example.android.itcreditonline.Model.ReadRss;

import java.util.ArrayList;


public class NewsFragment extends Fragment {
    private Activity activity;
    private RecyclerView newsRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_news, container, false);

        newsRV = (RecyclerView) view.findViewById(R.id.newsRecyclerView);

        ReadRss rss = new ReadRss(activity,newsRV);
        rss.execute();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }


}
