package com.example.androidserminar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidserminar.DatabaseSqlite.DBHandlerUsers;

public class RegisterAccountActivity extends AppCompatActivity {
    private EditText edtUsername, edtPassword, edtEmail, edtPhone;
    private Button btnRegister;
    private DBHandlerUsers dbHandlerUsers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        btnRegister = findViewById(R.id.btnRegister);

        // creating a new DBHandlerUsers class
        // and passing our context to it.
        dbHandlerUsers = new DBHandlerUsers(RegisterAccountActivity.this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
                Intent intent = new Intent(RegisterAccountActivity.this, LoginUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertUser(){
        String user = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(user)){
            edtUsername.setError("Username can be not empty");
            return;
        }
        if (TextUtils.isEmpty(password)){
            edtPassword.setError("Password can be not empty");
            return;
        }
        if (TextUtils.isEmpty(email)){
            edtEmail.setError("Email can be not empty");
            return;
        }
        if (TextUtils.isEmpty(phone)){
            edtPhone.setError("Phone can be not empty");
            return;
        }
        long result = dbHandlerUsers.addNewUser(user, password, email, phone);
        if (result == -1){
            Toast.makeText(RegisterAccountActivity.this, "Can be not insert", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(RegisterAccountActivity.this, "Insert database successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
