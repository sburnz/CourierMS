package com.example.courierms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ForgotPwActivity extends AppCompatActivity {

//    ImageView btBack;
//    TextView toolbarTitle;
//    TextView tvClear;
    Button btSendPw;
    TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pw);

//        btBack = findViewById(R.id.btBack);
//        toolbarTitle = findViewById(R.id.toolbarTitle);
//        tvClear = findViewById(R.id.tvClear);
//
//        btBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ForgotPwActivity.this, HomeActivity.class));
//            }
//        });
//        toolbarTitle.setText("Forgot password");
//        tvClear.setText("");

        btSendPw = findViewById(R.id.btSendPw);
        tvBack = findViewById(R.id.tvBack);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPwActivity.this, SignInActivity.class));
            }
        });
    }
}