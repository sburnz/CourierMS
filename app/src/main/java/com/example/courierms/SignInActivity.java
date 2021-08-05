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
import android.widget.TextView;
import android.widget.Toast;

import com.example.courierms.Remote.ApiService;
import com.example.courierms.Remote.ApiUtil;
import com.example.courierms.model.LoginResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    TextView tvNotYet1;
    Button btSignIn;
    TextView tvForgotPassword;

    Boolean isEmailValid, isPasswordValid;

    //Declaring SharedPreference Class
    SharedPreferences sh;

    //SharedPreference key names
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Initializing SharedPreference CLass
        sh = getSharedPreferences("MyPref", MODE_PRIVATE);

        //check if "LOGGED_IN" inside sharedpref is true then go to home activity
        String email = sh.getString(KEY_EMAIL, null);
        if (email != null) {
            startActivity(new Intent(SignInActivity.this, HomeActivity.class));
//            finish();
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvNotYet1 = findViewById(R.id.tvNotYet1);
        btSignIn = findViewById(R.id.btSignIn);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        tvNotYet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

//                startActivity(new Intent(SignInActivity.this, NewOrderActivity.class));
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, ForgotPwActivity.class));
            }
        });
    }

    private void signIn() {

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

        if (isEmailValid && isPasswordValid) {

            ApiService apiService = ApiUtil.getApiService();
            apiService.userLogin(etEmail.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Log.e("LOGINACTIVITY", "Status code = " + response.code());
                    if (response.code() == 200 || response.code() == 201) {
//                        try {
//                            Toast.makeText(SignInActivity.this, "" + response.body().string(), Toast.LENGTH_LONG).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                        //save sharedpreference "LOGGED_IN" as true
                        SharedPreferences.Editor editor = sh.edit();
                        editor.putString(KEY_USER_ID, response.body().getUser().getId().toString());
                        editor.putString(KEY_EMAIL, response.body().getUser().getEmail());
                        editor.putString(KEY_PASSWORD, etPassword.getText().toString());
                        editor.apply();

                        etEmail.getText().clear();
                        etPassword.getText().clear();

                        startActivity(new Intent(SignInActivity.this, HomeActivity.class));

//                    finish();
//                    startActivity(getIntent());
                    } else {
                        Toast.makeText(SignInActivity.this, "Invalid email or password. Try Again!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(SignInActivity.this, "On Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}