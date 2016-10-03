package com.example.android.itcreditonline;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.itcreditonline.Model.Database.DBManager;
//TODO remove later
import com.example.android.itcreditonline.Model.ReadRss;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_FOR_REGISTER = 1;
    private Button loginButton;
    private Button registerButton;
    private EditText usernameET;
    private EditText passwordET;
    private Dialog errorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginButton = (Button) findViewById(R.id.button_login);
        registerButton = (Button) findViewById(R.id.button_regiter);
        usernameET = (EditText) findViewById(R.id.username_et);
        passwordET = (EditText) findViewById(R.id.password_et);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameET.getText().toString().trim().isEmpty()){
                    usernameET.setError("Please enter your username");
                    usernameET.requestFocus();
                    return;
                }
                if(passwordET.getText().toString().trim().isEmpty()){
                    passwordET.setError("Please enter your password");
                    passwordET.requestFocus();
                    return;
                }

                //if user exists
                if(DBManager.getInstance(LoginActivity.this).validateUser(usernameET.getText().toString(),passwordET.getText().toString())){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("loggedUser", usernameET.getText().toString());
                    startActivity(intent);
                }else{
                    usernameET.setText("");
                    passwordET.setText("");
                    usernameET.requestFocus();
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                usernameET.setError(null);
                startActivity(intent);
            }
        });



    }
}
