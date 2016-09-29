package com.example.android.itcreditonline;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.itcreditonline.Model.ReadRss;

/**
 * Created by Aydin on 28.9.2016 г..
 */

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment_view, parent, false);

    }
}
