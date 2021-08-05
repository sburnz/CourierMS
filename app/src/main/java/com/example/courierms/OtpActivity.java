package com.example.courierms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courierms.Remote.ApiService;
import com.example.courierms.Remote.ApiUtil;
import com.example.courierms.model.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {

    EditText etOtp;
    Button btVerify;
    TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        etOtp = findViewById(R.id.etOtp);
        btVerify = findViewById(R.id.btVerify);
        tvBack = findViewById(R.id.tvBack);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtpActivity.this, SignUpActivity.class));
            }
        });

        btVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });
    }

    private void verify() {
        ApiService apiService = ApiUtil.getApiService();
        apiService.userVerify(etOtp.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.e("OTPACTIVITY", "Status code = " + response.code());
                        if (response.code() == 200 || response.code() == 201 || response.code() == 500) {
//                    try {
//                        Toast.makeText(OtpActivity.this, "" + response.body().string(), Toast.LENGTH_SHORT).show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                            etOtp.getText().clear();

                            Toast.makeText(OtpActivity.this, "Verification successful!", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(OtpActivity.this, HomeActivity.class));

//                            finish();
//                    startActivity(getIntent());
                        } else {
                            Toast.makeText(OtpActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(OtpActivity.this, "On Failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}