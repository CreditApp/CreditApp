package com.example.android.itcreditonline;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.itcreditonline.Model.Database.DBManager;
import com.example.android.itcreditonline.Model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreditsFragment extends Fragment {
    private Activity activity;
    private RecyclerView newsRV;
    private CreditsAdapter creditsAdapter;

    public CreditsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.activity =(Activity) activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_credits, container, false);

        User u = DBManager.getInstance(activity).getUser(activity.getIntent().getStringExtra("loggedUser"));
        creditsAdapter = new CreditsAdapter(activity,u.getCredits());
        newsRV = (RecyclerView) root.findViewById(R.id.creditsRecyclerView);
        newsRV.setLayoutManager(new LinearLayoutManager(activity));
        newsRV.setAdapter(creditsAdapter);

        return root ;
    }

    public void refreshsAdapter() {
        if(creditsAdapter!= null)
        creditsAdapter.notifyDataSetChanged();
    }
}
