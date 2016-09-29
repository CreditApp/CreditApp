package com.example.android.itcreditonline.Model;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by Aydin on 29.9.2016 г..
 */
public class UsersManager {
    private static UsersManager ourInstance ;
    private HashMap<String, User> userInfo;
    public static UsersManager getInstance() {
        if(ourInstance==null){
            ourInstance = new UsersManager();
        }
        return ourInstance;
    }

    private UsersManager() {
        userInfo = new HashMap<>();

    }


    public void registerUser(String username, String name, String surname, String pass, String email, String phoneNumber, String id){
        User user = new User(username,name,surname,pass,email,phoneNumber,id);
        userInfo.put(username,user);

    }

    public static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static boolean validate(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }


}
