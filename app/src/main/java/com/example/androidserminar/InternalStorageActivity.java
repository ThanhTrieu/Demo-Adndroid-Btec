package com.example.androidserminar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalStorageActivity extends AppCompatActivity {
    private EditText editName, editPass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_internal_storage);
        editName = (EditText) findViewById(R.id.editName);
        editPass = (EditText) findViewById(R.id.editPass);
    }
    public void  saveMe(View view) {
        File file= null;
        String name = editName.getText().toString().trim();
        String password = editPass.getText().toString().trim();

        FileOutputStream fileOutputStream = null;
        try {
            name = name + " ";
            file = getFilesDir();
            fileOutputStream = openFileOutput("Code.txt", Context.MODE_PRIVATE); //MODE PRIVATE
            fileOutputStream.write(name.getBytes());
            fileOutputStream.write(password.getBytes());
            Toast.makeText(this, "Saved \n" + "Path --" + file + "\tCode.txt", Toast.LENGTH_SHORT).show();
            editName.setText("");
            editPass.setText("");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void  nextMe(View view)  {
        Toast.makeText(this,"NEXT", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(InternalStorageActivity.this, InternalStorageSecondActivity.class);
        startActivity(intent);
    }
}
