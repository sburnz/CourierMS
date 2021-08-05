package com.example.courierms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangePwActivity extends AppCompatActivity {

    ImageView btBack;
    TextView toolbarTitle;
    TextView tvClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);

        btBack = findViewById(R.id.btBack);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        tvClear = findViewById(R.id.tvClear);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePwActivity.this, HomeActivity.class));
            }
        });
        toolbarTitle.setText("Change Password");
        tvClear.setText("");
    }
}