package com.example.android.itcreditonline.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.android.itcreditonline.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aydin on 29.9.2016 г..
 */

public class DBManager extends SQLiteOpenHelper {
    private static DBManager ourInstance;
    private static int version = 1;
    private Context context;
    private HashMap<String, User> registerredUsers;//username -> User


    public static DBManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DBManager(context);
        }
        return ourInstance;
    }

    private DBManager(Context context) {
        super(context, "CreditAppDB", null, version);
        registerredUsers = new HashMap<>();
        this.context = context;
        loadUsers();
    }

    private void loadUsers() {
        if (registerredUsers.isEmpty()) {
            //select all users from db
            Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM TABLE_USERS", null);
            while (cursor.moveToNext()) {

                String username = cursor.getString(0);
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                String password = cursor.getString(3);
                String email = cursor.getString(4);
                String phoneNumber = cursor.getString(5);
                String address = cursor.getString(6);
                String id = cursor.getString(7);

                User user = new User(username, name, surname, password, email, phoneNumber,address, id);
                registerredUsers.put(username, user);
            
                //Log for register users
                List<User> users = new ArrayList<>();
                for (User u : registerredUsers.values()) {
                        users.add(u);
                }
                Log.e("Register users", users.toString());
            }


        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE TABLE_USERS (USERS_USERNAME text,USERS_NAME text, USERS_SURNAME text, USERS_PASSWORD text, USERS_EMAIL text, USERS_PHONE_NUMBER text,USERS_ADDRESS text, USERS_ID text)");
//        db.execSQL("CREATE TABLE TABLE_CREDITS (CREDITS_ID int , CREDITS_OWNER text, CREDITS_DATE text, CREDITS_DURATION text,PRIMARY KEY(CREDITS_ID), FOREIGN KEY(CREDITS_OWNER) REFERENCES TABLE_USERS(USERS_USERNAME)");
        

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE users");
        onCreate(db);
    }

    public void registerUser(String username, String name, String surname, String pass, String email, String phoneNumber,String address, String id) {
        ContentValues values = new ContentValues();
        values.put("USERS_USERNAME", username);
        values.put("USERS_NAME", name);
        values.put("USERS_SURNAME", surname);
        values.put("USERS_PASSWORD", pass);
        values.put("USERS_EMAIL", email);
        values.put("USERS_PHONE_NUMBER", phoneNumber);
        values.put("USERS_ADDRESS",address);
        values.put("USERS_ID", id);
        getWritableDatabase().insert("TABLE_USERS", null, values);
        registerredUsers.put(username, new User(username, name, surname, pass, email, phoneNumber,address, id));

    }

    public static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static boolean validatePhone(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public boolean validateUser(String username, String password) {
        if (existsUser(username)) {
            return registerredUsers.get(username).getPass().equals(password);
        }
        return false;
    }

    public boolean existsUser(String username) {
        return registerredUsers.containsKey(username);
    }

    public void deleteUser(String username) {
        getWritableDatabase().delete("TABLE_USERS", "USERS_USERNAME = ?", new String[]{username});
    }
}


