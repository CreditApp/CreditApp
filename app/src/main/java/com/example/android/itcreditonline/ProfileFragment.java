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
import android.widget.EditText;
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
    private TextView surnameTV;
    private TextView addressTV;
    private TextView emailTV;
    private TextView phoneTV;
    private TextView idTV;
    private String username;
    private Button logoutBtn;
    private EditText surnameET;
    private EditText phoneNumberET;
    private EditText emailET;
    private EditText addressET;
    private Button changeSurnameBTN;
    private Button changeEmailBTN;
    private Button changePhoneBTN;
    private Button changeAddressBTN;
    private Button confirnSurnameBTN;
    private Button confirmEmailBTN;
    private Button confirmPhoneBTN;
    private Button confirmAddressBTN;

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
        surnameET = (EditText) root.findViewById(R.id.user_surname_et);
        emailET = (EditText) root.findViewById(R.id.user_email_et);
        addressET = (EditText)root.findViewById(R.id.user_address_et);
        phoneNumberET = (EditText)root.findViewById(R.id.user_phone_et);
        changeSurnameBTN = (Button) root.findViewById(R.id.btn_change_surname);
        confirnSurnameBTN = (Button) root.findViewById(R.id.btn_confirm_surname);
        changeAddressBTN = (Button) root.findViewById(R.id.btn_change_address);
        confirmAddressBTN = (Button) root.findViewById(R.id.btn_confirm_address);
        changeEmailBTN = (Button) root.findViewById(R.id.btn_change_email);
        confirmEmailBTN = (Button) root.findViewById(R.id.btn_confirm_email);
        changePhoneBTN = (Button) root.findViewById(R.id.btn_change_phone);
        confirmPhoneBTN = (Button) root.findViewById(R.id.btn_confirm_phone);
        username = activity.getIntent().getStringExtra("loggedUser");
        final User u = DBManager.getInstance(activity).getUser(username);
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

        if(changeSurnameBTN !=null) {
            changeSurnameBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    surnameTV.setVisibility(View.GONE);
                    surnameET.setText(u.getSurname());
                    surnameET.setVisibility(View.VISIBLE);
                    surnameET.requestFocus();
                    changeSurnameBTN.setVisibility(View.GONE);
                    confirnSurnameBTN.setVisibility(View.VISIBLE);
                }
            });
        }
    if(confirnSurnameBTN!=null) {
        confirnSurnameBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surnameET.getText().toString().isEmpty()) {
                    surnameET.setError("Please enter your new surname");
                    return;
                }
                surnameTV.setVisibility(View.VISIBLE);
                surnameTV.setText(surnameET.getText().toString());
                surnameET.setVisibility(View.GONE);
                changeSurnameBTN.setVisibility(View.VISIBLE);
                confirnSurnameBTN.setVisibility(View.GONE);
                DBManager.getInstance(activity).changeSurname(username, surnameET.getText().toString());

            }
        });
    }
        if(changeAddressBTN !=null) {
            changeAddressBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addressTV.setVisibility(View.GONE);
                    addressET.setText(u.getAddress());
                    addressET.setVisibility(View.VISIBLE);
                    addressET.requestFocus();
                    changeAddressBTN.setVisibility(View.GONE);
                    confirmAddressBTN.setVisibility(View.VISIBLE);
                }
            });
        }
        if(confirmAddressBTN!=null) {
            confirmAddressBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (addressET.getText().toString().isEmpty()) {
                        addressET.setError("Please enter your new address");
                        return;
                    }
                    addressTV.setVisibility(View.VISIBLE);
                    addressTV.setText(addressET.getText().toString());
                    addressET.setVisibility(View.GONE);
                    changeAddressBTN.setVisibility(View.VISIBLE);
                    confirmAddressBTN.setVisibility(View.GONE);
                    DBManager.getInstance(activity).changeAddress(username, addressET.getText().toString());

                }
            });
        }
        if(changeEmailBTN !=null) {
            changeEmailBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emailTV.setVisibility(View.GONE);
                    emailET.setText(u.getEmail());
                    emailET.setVisibility(View.VISIBLE);
                    emailET.requestFocus();
                    changeEmailBTN.setVisibility(View.GONE);
                    confirmEmailBTN.setVisibility(View.VISIBLE);
                }
            });
        }
        if(confirmEmailBTN!=null) {
            confirmEmailBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (emailET.getText().toString().isEmpty()) {
                        emailET.setError("Please enter your new email");
                        return;
                    }
                    if (!DBManager.isValidEmail(emailET.getText().toString())) {
                        emailET.setError("Invalid email");
                        emailET.requestFocus();
                        return;
                    }
                    emailTV.setVisibility(View.VISIBLE);
                    emailTV.setText(emailET.getText().toString());
                    emailET.setVisibility(View.GONE);
                    changeEmailBTN.setVisibility(View.VISIBLE);
                    confirmEmailBTN.setVisibility(View.GONE);
                    DBManager.getInstance(activity).changeEmail(username, emailET.getText().toString());

                }
            });
        }

        if(changePhoneBTN !=null) {
            changePhoneBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phoneTV.setVisibility(View.GONE);
                    phoneNumberET.setText(u.getPhoneNumber());
                    phoneNumberET.setVisibility(View.VISIBLE);
                    phoneNumberET.requestFocus();
                    changePhoneBTN.setVisibility(View.GONE);
                    confirmPhoneBTN.setVisibility(View.VISIBLE);
                }
            });
        }
        if(confirmPhoneBTN!=null) {
            confirmPhoneBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (phoneNumberET.getText().toString().isEmpty()) {
                        phoneNumberET.setError("Please enter your new phone number");
                        return;
                    }
                    if(!DBManager.validatePhone(phoneNumberET.getText().toString())){
                        phoneNumberET.setError("Please enter a valid phone number");
                        phoneNumberET.requestFocus();
                        return;
                    }
                    phoneTV.setVisibility(View.VISIBLE);
                    phoneTV.setText(phoneNumberET.getText().toString());
                    phoneNumberET.setVisibility(View.GONE);
                    changePhoneBTN.setVisibility(View.VISIBLE);
                    confirmPhoneBTN.setVisibility(View.GONE);
                    DBManager.getInstance(activity).changePhone(username, phoneNumberET.getText().toString());

                }
            });
        }
        return root;

    }
}
