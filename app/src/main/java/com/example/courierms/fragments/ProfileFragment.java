package com.example.courierms.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.courierms.AboutActivity;
import com.example.courierms.ChangePwActivity;
import com.example.courierms.ManageProfileActivity;
import com.example.courierms.NewOrderActivity;
import com.example.courierms.R;
import com.example.courierms.ServiceInfoActivity;
import com.example.courierms.SignInActivity;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    TextView tvHello;
    TextView tvEmail;
    Button btServiceInfo;
    Button btAbout;
    Button btManageProfile;
    Button btChangePw;
    Button btLogOut;

    SharedPreferences sh;
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_FULL_NAME = "fullName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        sh = this.getActivity().getSharedPreferences("MyPref", MODE_PRIVATE);

        String userId = sh.getString(KEY_USER_ID, null);
        String fullName =  sh.getString(KEY_FULL_NAME, null);
        String email =  sh.getString(KEY_EMAIL, null);
        String password =  sh.getString(KEY_PASSWORD, null);


        tvEmail = (TextView) getView().findViewById(R.id.tvEmail);
        tvHello = (TextView) getView().findViewById(R.id.tvHello);
        btServiceInfo = (Button) getView().findViewById(R.id.btServiceInfo);
        btAbout = (Button) getView().findViewById(R.id.btAbout);
        btManageProfile = (Button) getView().findViewById(R.id.btManageProfile);
        btChangePw = (Button) getView().findViewById(R.id.btChangePw);
        btLogOut = (Button) getView().findViewById(R.id.btLogOut);

        tvEmail.setText(email);
//        tvHello.setText("Hello "+fullName+"!");

        btServiceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ServiceInfoActivity.class));
            }
        });

        btAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (email != null || password != null) {
//                    Toast.makeText(getActivity(), "Email: "+email+" & Password: "+password, Toast.LENGTH_SHORT).show();
//                }

                startActivity(new Intent(getContext(), AboutActivity.class));
            }
        });

        btManageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ManageProfileActivity.class));
            }
        });

        btChangePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ChangePwActivity.class));
            }
        });

        btLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sh.edit();
                editor.clear();
                editor.commit();

                Toast.makeText(getActivity(),"Logged out successfully.", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getContext(), SignInActivity.class));
                getActivity().finish();
            }
        });
    }
}
