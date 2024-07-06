package com.example.androidserminar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestIntent extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent);
        Button btnActivity = findViewById(R.id.btnStartActivityDemoIntent);
        TextView tvUrl = findViewById(R.id.tvUrl);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String value1 = bundle.getString("Key_url", "");
            int value2 = bundle.getInt("Key_2", 0);
            boolean value3 = bundle.getBoolean("Key_3", false);
            tvUrl.setText(value1);
        }


        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DemoIntent.class);
                startActivity(intent);
            }
        });
    }
}
