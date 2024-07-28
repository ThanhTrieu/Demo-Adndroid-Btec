package com.example.androidserminar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidserminar.DatabaseSqlite.DBHandlerUsers;
import com.example.androidserminar.Model.User;

public class LoginUserActivity extends AppCompatActivity {
    private EditText edtUsername, edtPassword;
    private Button btnSubmit;
    private DBHandlerUsers dbHandlerUsers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_account);
        edtUsername = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPass);
        btnSubmit = findViewById(R.id.btnLogin);
        dbHandlerUsers = new DBHandlerUsers(LoginUserActivity.this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User data = login();
                assert data != null;
                String email = data.getEmail();
                Toast.makeText(LoginUserActivity.this, email, Toast.LENGTH_LONG).show();
            }
        });
    }
    private User login(){
        String user = edtUsername.getText().toString().trim();
        String pass = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(user)){
            edtUsername.setError("Username can be not empty");
            return null;
        }
        if (TextUtils.isEmpty(pass)){
            edtPassword.setError("Password can be not empty");
            return null;
        }
        return dbHandlerUsers.getSingleUserInfo(user, pass);
    }
}
