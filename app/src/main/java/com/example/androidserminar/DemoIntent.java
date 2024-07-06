package com.example.androidserminar;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class DemoIntent extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_intent);
        Button btnUrl = findViewById(R.id.btnUrl);
        EditText edtUrl = findViewById(R.id.edtUrl);
        Button btnPhone = findViewById(R.id.btnCallPhone);
        EditText edtPhone = findViewById(R.id.edtPhoneNumber);
        Button gotoActivity = findViewById(R.id.btnGotoActivity);
        Button btnSendData = findViewById(R.id.btnSendData);
        btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtUrl.getText().toString().trim();
                if(TextUtils.isEmpty(url)){
                    Toast.makeText(DemoIntent.this, "Url is not empty", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            }
        });
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edtPhone.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phone));
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
            }
        });
        gotoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TestIntent.class);
                startActivity(intent);
            }
        });
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtUrl.getText().toString().trim();
                Intent intent = new Intent(DemoIntent.this, TestIntent.class);
                Bundle bundle = new Bundle();
                bundle.putString("Key_url", url);
                bundle.putInt("Key_2", 5);
                bundle.putBoolean("Key_3", true);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
