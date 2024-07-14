package com.example.androidserminar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferencesActivity extends AppCompatActivity {
    private EditText edtNumber1, edtNumber2, edtResult;
    private Button btnSum, btnClear;
    private TextView tvHistory;
    private String history = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        edtResult = findViewById(R.id.edtResult);
        edtNumber1 = findViewById(R.id.edtNumber1);
        edtNumber2 = findViewById(R.id.edtNumber2);
        btnSum = findViewById(R.id.btnSum);
        btnClear = findViewById(R.id.btnClear);
        tvHistory = findViewById(R.id.tvHistory);
        edtResult.setEnabled(false);

        //------Lấy lại dữ liệu trong SharedPreferences--------------------------------
        SharedPreferences myPrefs = getSharedPreferences("mySave",MODE_PRIVATE);
        history = myPrefs.getString("myHistory","");
        tvHistory.setText(history);
        //------------------------------------------------------------------------------

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtNumber1.getText().toString());
                int b = Integer.parseInt(edtNumber2.getText().toString());
                int kq = a + b;
                edtResult.setText(String.format("%d", kq));
                history += a + " + " + b + " = " + kq;
                tvHistory.setText(history);
                history +="\n";
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history ="";
                tvHistory.setText(history);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences myPrefs = getSharedPreferences("mySave",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = myPrefs.edit();
        myEdit.putString("myHistory",history);
        myEdit.apply();
    }
}
