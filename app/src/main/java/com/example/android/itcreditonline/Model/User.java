package com.example.android.itcreditonline.Model;

import java.util.ArrayList;

/**
 * Created by Aydin on 29.9.2016 г..
 */

public class User {
    private String username;
    private String name;
    private String surname;
    private String pass;
    private String email;
    private String phoneNumber;
    private String id;
    private ArrayList<Credit> credits;

    public User(String username, String name, String surname, String pass, String email, String phoneNumber, String id) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.pass = pass;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
        credits = new ArrayList<>();
    }




}
