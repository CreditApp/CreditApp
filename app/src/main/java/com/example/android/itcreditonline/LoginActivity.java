package com.example.android.itcreditonline;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.itcreditonline.Model.Database.DBManager;

public class LoginActivity extends AppCompatActivity {

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
                //if user exists
                if(DBManager.getInstance(LoginActivity.this).validateUser
                        (usernameET.getText().toString(),passwordET.getText().toString())){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("loggedUser", usernameET.getText().toString());
                    startActivity(intent);
                }else{
                    errorLogin = new Dialog(LoginActivity.this);
                    errorLogin.setContentView(R.layout.dialog_wrong_user_or_pass);
                    errorLogin.setTitle("Error");
                    errorLogin.show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
