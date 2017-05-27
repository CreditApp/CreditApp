package com.example.android.itcreditonline;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.itcreditonline.Model.FeedItem;
import com.example.android.itcreditonline.Model.ReadRss;

import java.util.ArrayList;


public class NewsFragment extends Fragment {
    private Activity activity;
    private RecyclerView newsRV;
    private TextView noInternet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        newsRV = (RecyclerView) view.findViewById(R.id.newsRecyclerView);
        noInternet = (TextView) view.findViewById(R.id.noInternet);

        ReadRss rss = new ReadRss(activity, newsRV);

        if (isNetworkAvailable()) {
           rss.execute("http://www.centralbanking.com/feeds/rss/category/risk-management");
            noInternet.setVisibility(View.GONE);
        }


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

}
