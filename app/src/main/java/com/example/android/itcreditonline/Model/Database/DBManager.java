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

import java.util.HashMap;

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
            Cursor cursor = getWritableDatabase().rawQuery("SELECT username, password FROM users", null);
            while (cursor.moveToNext()) {

                String username = cursor.getString(cursor.getColumnIndex("username"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String surname = cursor.getString(cursor.getColumnIndex("surname"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
                String id = cursor.getString(cursor.getColumnIndex("id"));
                //create instances of each user for each row
                User user = new User(username, name, surname, password, email, phoneNumber, id);
                //fill all users in the map
                registerredUsers.put(username, user);
            }
            if (!cursor.isClosed())
                cursor.close();
            StringBuilder allUsers = new StringBuilder();
            for (User u : registerredUsers.values()) {
                allUsers.append(u.getUsername()).append(" ").append(u.getPass()).append("\n");
            }
            Log.e("maikaTi", allUsers.toString());
            Toast.makeText(context, "Users loaded: \n" + allUsers.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (username text,name text, surname text, password text, email text, phoneNumber text, id text PRIMARY KEY)");
        Toast.makeText(context, "DB created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE users");
        onCreate(db);
    }

    public void registerUser(String username, String name, String surname, String pass, String email, String phoneNumber, String id) {
        User user = new User(username, name, surname, pass, email, phoneNumber, id);
        getWritableDatabase().beginTransaction();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("name", name);
        values.put("surname", surname);
        values.put("pass", pass);
        values.put("email", email);
        values.put("phoneNumber", name);
        values.put("id", name);
        getWritableDatabase().insert("users", null, values);
        registerredUsers.put(username, user);

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
        getWritableDatabase().delete("users", "username = ?", new String[]{username});
    }
}


