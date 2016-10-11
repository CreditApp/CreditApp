package com.example.android.itcreditonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.itcreditonline.Model.Database.DBManager;

public class RegisterActivity extends AppCompatActivity {

    private EditText password;
    private EditText username;
    private EditText name;
    private EditText surName;
    private EditText email;
    private EditText passwordAgain;
    private EditText address;
    private Button btnRegister;
    private EditText phone;
    private EditText id;
    public static final int REG_SUCCCSSFULLY=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        password = (EditText) findViewById(R.id.passwordAdd);
        username = (EditText) findViewById(R.id.usernameAdd);
        name = (EditText) findViewById(R.id.nameAdd);
        email = (EditText) findViewById(R.id.email);
        passwordAgain = (EditText) findViewById(R.id.repeatPassword);
        address = (EditText) findViewById(R.id.address);
        btnRegister = (Button) findViewById(R.id.btn_registerAdd);
        phone = (EditText) findViewById(R.id.namePhone);
        surName = (EditText) findViewById(R.id.surNameAdd);
        id = (EditText) findViewById(R.id.id_et);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DBManager.getInstance(RegisterActivity.this).existsUser(username.getText().toString())) {
                    username.setError("User with this name already exist");
                    username.requestFocus();
                    return;
                }

                if (username.getText().toString().trim().length() < 6) {
                    username.setError("Username must be more than 5 symbols");
                    username.requestFocus();
                    return;
                }


                if (name.getText().toString().trim().isEmpty()) {
                    name.setError("Invalid name");
                    name.requestFocus();
                    return;
                }

                if (surName.getText().toString().trim().isEmpty()) {
                    surName.setError("Invalid surname");
                    surName.requestFocus();
                    return;
                }

                if (password.getText().toString().length() < 6) {
                    password.setError("Password must be more than 6 symbols.");
                    password.requestFocus();
                    return;
                }

                if (!password.getText().toString().equals(passwordAgain.getText().toString())) {
                    passwordAgain.setError("Password is not the same.");
                    passwordAgain.requestFocus();
                    return;
                }
                if (!DBManager.isValidEmail(email.getText().toString())) {
                    email.setError("Invalid email");
                    email.requestFocus();
                    return;
                }
                if (address.getText().toString().trim().isEmpty()) {
                    address.setError("Please enter your address!");
                    address.requestFocus();
                    return;
                }
                
                if(id.getText().toString().trim().isEmpty()){
                    id.setError("Please enter your id");
                    id.requestFocus();
                    return;
                }

                if(!DBManager.validatePhone(phone.getText().toString())){
                    phone.setError("Please enter a valid phone number");
                    phone.requestFocus();
                    return;
                }

                DBManager.getInstance(RegisterActivity.this).registerUser(username.getText().toString(), name.getText().toString(),surName.getText().toString(), password.getText().toString(), email.getText().toString(), phone.getText().toString(),address.getText().toString(), id.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("username",username.getText().toString());
                intent.putExtra("password",password.getText().toString());
                setResult(REG_SUCCCSSFULLY,intent);
                finish();
            }

        });

    }
}
