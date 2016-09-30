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
    private String address;
    private String id;
    private ArrayList<Credit> credits;

    public User(String username, String name, String surname, String pass, String email, String phoneNumber,String address, String id) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.pass = pass;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.id = id;
        credits = new ArrayList<>();
    }

    public  String getAddress(){return  address;}
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    
}
