package com.example.android.itcreditonline;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.itcreditonline.Model.Database.DBManager;
import com.example.android.itcreditonline.Model.User;

/**
 * Created by Aydin on 28.9.2016 г..
 */

public class ProfileFragment extends Fragment {
    private Activity activity;
    private TextView usernameTV;
    private TextView nameTV;
    private  TextView surnameTV;
    private TextView addressTV;
    private TextView emailTV;
    private TextView phoneTV;
    private TextView idTV;
    private String username;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.activity = (Activity) activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.profile_fragment_view, parent, false);
        usernameTV = (TextView) root.findViewById(R.id.user_username);
        nameTV = (TextView) root.findViewById(R.id.user_name);
        surnameTV=(TextView) root.findViewById(R.id.user_surname);
        addressTV = (TextView) root.findViewById(R.id.user_address);
        emailTV = (TextView) root.findViewById(R.id.user_email);
        phoneTV = (TextView) root.findViewById(R.id.user_phone);
        idTV = (TextView) root.findViewById(R.id.user_id);
        username = activity.getIntent().getStringExtra("loggedUser");
        User u = DBManager.getInstance(activity).getUser(username);
        usernameTV.setText(u.getUsername());
        nameTV.setText(u.getName());
        surnameTV.setText(u.getSurname());
        addressTV.setText(u.getAddress());
        emailTV.setText(u.getEmail());
        phoneTV.setText(u.getPhoneNumber());
        idTV.setText(u.getId());
        return root;

    }
}
