package com.example.android.itcreditonline;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.itcreditonline.Model.Database.DBManager;
import static com.example.android.itcreditonline.RegisterActivity.REG_SUCCCSSFULLY;


public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_FOR_REGISTER = 1;
    private Button loginButton;
    private Button registerButton;
    private EditText usernameET;
    private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String loggedUser = DBManager.getInstance(this).getLastLoggedUser();

        if (!loggedUser.equals("No logged user!")) {
            Intent login = new Intent(LoginActivity.this, MainActivity.class);
            login.putExtra("loggedUser", loggedUser);
            startActivity(login);
            finish();
        }

        loginButton = (Button) findViewById(R.id.btn_login);
        registerButton = (Button) findViewById(R.id.register_btn);
        usernameET = (EditText) findViewById(R.id.username_et);
        passwordET = (EditText) findViewById(R.id.password_et);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameET.getText().toString().trim().isEmpty()) {
                    usernameET.setError("Please enter your username");
                    usernameET.requestFocus();
                    return;
                }
                if (passwordET.getText().toString().trim().isEmpty()) {
                    passwordET.setError("Please enter your password");
                    passwordET.requestFocus();
                    return;
                }

                //if user exists
                if (DBManager.getInstance(LoginActivity.this).validateUser(usernameET.getText().toString(), passwordET.getText().toString())) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("loggedUser", usernameET.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
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
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                usernameET.setError(null);
                startActivityForResult(intent,REQUEST_FOR_REGISTER);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_FOR_REGISTER){
            if(resultCode == REG_SUCCCSSFULLY){
                usernameET.setText(data.getStringExtra("username"));
                passwordET.setText(data.getStringExtra("password"));
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
