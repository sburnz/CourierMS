package com.example.courierms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.courierms.model.CourierModel;
import com.example.courierms.model.Model;

import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {

    ImageView btBack;
    TextView toolbarTitle;
    TextView tvClear;

    TextView tvItem;
    TextView tvWeight;
    TextView tvParcelValue;

    TextView tvSender;
    TextView tvSenderAddress;
    TextView tvPickUpTime;
    TextView tvSenderPhone;
    TextView tvSenderComments;

    TextView tvReceiver;
    TextView tvReceiverAddress;
    TextView tvDeliveryTime;
    TextView tvReceiverPhone;
    TextView tvReceiverComments;

    SharedPreferences sh;
    private static final String KEY_USER_ID = "userId";

    List<CourierModel> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btBack = findViewById(R.id.btBack);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        tvClear = findViewById(R.id.tvClear);

        tvItem = findViewById(R.id.tvItem);
        tvWeight = findViewById(R.id.tvWeight);
        tvParcelValue = findViewById(R.id.tvParcelValue);

        tvSender = findViewById(R.id.tvSender);
        tvSenderAddress = findViewById(R.id.tvSenderAddress);
        tvPickUpTime = findViewById(R.id.tvPickUpTime);
        tvSenderPhone = findViewById(R.id.tvSenderPhone);
        tvSenderComments = findViewById(R.id.tvSenderComments);

        tvReceiver = findViewById(R.id.tvReceiver);
        tvReceiverAddress = findViewById(R.id.tvReceiverAddress);
        tvDeliveryTime = findViewById(R.id.tvDeliveryTime);
        tvReceiverPhone = findViewById(R.id.tvReceiverPhone);
        tvReceiverComments = findViewById(R.id.tvReceiverComments);

        Intent intent=getIntent();
        String item =intent.getStringExtra("item");
        String weight =intent.getStringExtra("weight");
        String parval =intent.getStringExtra("parval");

        tvItem.setText("What's in it: " + item);
        tvWeight.setText("Up to " + weight + " kg");
        tvParcelValue.setText("Stated value: Rs. " + parval);





        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, SignUpActivity.class));
            }
        });
        toolbarTitle.setText("Order Details");
        tvClear.setText("");
    }
}