package com.example.android.itcreditonline;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button logoutBtn;

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
        logoutBtn = (Button) root.findViewById(R.id.log_out_button);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager.getInstance(activity).logout();
                Intent intent = new Intent(activity,LoginActivity.class);
                startActivity(intent);
                activity.finish();
            }
        });
        return root;

    }
}
