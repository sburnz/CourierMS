package com.example.courierms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.courierms.Remote.ApiService;
import com.example.courierms.Remote.ApiUtil;
import com.example.courierms.model.LoginResponse;
import com.example.courierms.model.RegisterResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText etFullName;
    //    EditText etPhone;
    EditText etEmail;
    EditText etPassword;
    Button btSignUp;

    Boolean isEmailValid, isPasswordValid, isNameValid;

    //Declaring SharedPreference Class
    SharedPreferences sh;

    //SharedPreference key names
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_FULL_NAME = "fullName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //Initializing SharedPreference CLass
        sh = getSharedPreferences("MyPref", MODE_PRIVATE);

        etFullName = findViewById(R.id.etFullName);
//        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btSignUp = findViewById(R.id.btSignUp);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
//                startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
            }
        });

    }

    private void signUp() {

        // Check for a valid email id.
        if (etEmail.getText().toString().isEmpty()) {
            etEmail.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            etEmail.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else {
            isEmailValid = true;
        }

        // Check for a valid password.
        if (etPassword.getText().toString().isEmpty()) {
            etPassword.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (etPassword.getText().length() < 4) {
            etPassword.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
        }

        // Check for a valid name.
        if(etFullName.getText().toString().isEmpty()) {
            etFullName.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else {
            isNameValid = true;
        }

        if (isNameValid && isEmailValid && isPasswordValid) {

            ApiService apiService = ApiUtil.getApiService();
            apiService.userRegister(etFullName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString())
                    .enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            Log.e("REGISTERACTIVITY", "Status code = " + response.code());

                            try {
                                Log.v("TAGESTO", response.errorBody().string());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (response.code() == 200 || response.code() == 201) {
//                    try {
//                        Toast.makeText(SignUpActivity.this, "" + response.body().string(), Toast.LENGTH_SHORT).show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                                //save sharedpreference "LOGGED_IN" as true
                                SharedPreferences.Editor editor = sh.edit();
                                editor.putString(KEY_USER_ID, response.body().getId().toString());
                                editor.putString(KEY_FULL_NAME, etFullName.getText().toString());
                                editor.putString(KEY_EMAIL, response.body().getUser());
                                editor.putString(KEY_PASSWORD, etPassword.getText().toString());
                                editor.apply();

                                etFullName.getText().clear();
//                    etPhone.getText().clear();
                                etEmail.getText().clear();
                                etPassword.getText().clear();

                                Toast.makeText(SignUpActivity.this, "An OTP has been mailed to you. Please verify! ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, OtpActivity.class));

//                                startActivity(new Intent(SignUpActivity.this, HomeActivity.class));

//                    finish();
//                    startActivity(getIntent());
                            } else {
                                Toast.makeText(SignUpActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                            Toast.makeText(SignUpActivity.this, "On Failure", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


    }
}