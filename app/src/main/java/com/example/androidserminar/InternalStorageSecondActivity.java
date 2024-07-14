package com.example.androidserminar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;

public class InternalStorageSecondActivity extends AppCompatActivity {
    private TextView getName, getPass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        getName = findViewById(R.id.getname);
        getPass = findViewById(R.id.getpass);
    }

    public void  loadMe(View view)
    {
        try {
            FileInputStream fileInputStream =  openFileInput("Code.txt");
            int read = -1;
            StringBuilder buffer = new StringBuilder();
            while((read =fileInputStream.read())!= -1){
                buffer.append((char)read);
            }
            Log.d("Code", buffer.toString());
            String name = buffer.substring(0,buffer.indexOf(" "));
            String pass = buffer.substring(buffer.indexOf(" ")+1);
            getName.setText(name);
            getPass.setText(pass);
            Toast.makeText(this,"Load Success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"Load Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void  backMe( View view)
    {
        Toast.makeText(this,"Back", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(InternalStorageSecondActivity.this, InternalStorageActivity.class);
        startActivity(intent);
    }
}
