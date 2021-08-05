package com.example.courierms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courierms.Remote.ApiService;
import com.example.courierms.Remote.ApiUtil;
import com.example.courierms.model.CourierResponse;
import com.example.courierms.model.LoginResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.courierms.model.Data;
import com.example.courierms.model.OrderResponse;

public class SubmitOrderActivity extends AppCompatActivity {

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

    TextView tvFinalPrice;
    Button btSubmitOrder;

    SharedPreferences shOrder;
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_COURIER_ID = "courierId";

    private static final String KEY_ITEM = "item";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_PAR_VAL = "parVal";

    private static final String KEY_SENDER = "sender";
    private static final String KEY_PK_ADDRESS = "pkAddress";
    private static final String KEY_PK_DATE_TIME = "pkDateTime";
    private static final String KEY_PK_PHONE = "pkPhone";
    private static final String KEY_PK_COMMENT = "pkComment";

    private static final String KEY_RECEIVER = "receiver";
    private static final String KEY_RCV_ADDRESS = "rcvAddress";
    private static final String KEY_RCV_DATE_TIME = "rcvDateTime";
    private static final String KEY_RCV_PHONE = "rcvPhone";
    private static final String KEY_RCV_COMMENT = "rcvComment";

    private static final String KEY_PRICE = "price";

//    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);

        shOrder = getSharedPreferences("MyPref", MODE_PRIVATE);

        String userId = shOrder.getString(KEY_USER_ID, null);
        String courierId = shOrder.getString(KEY_COURIER_ID, null);

        String item = shOrder.getString(KEY_ITEM, null);
        String quantity = shOrder.getString(KEY_QUANTITY, null);
        String weight = shOrder.getString(KEY_WEIGHT, null);
        String parVal = shOrder.getString(KEY_PAR_VAL, null);

        String sender = shOrder.getString(KEY_SENDER, null);
        String pkAddress = shOrder.getString(KEY_PK_ADDRESS, null);
        String pkDateTime = shOrder.getString(KEY_PK_DATE_TIME, null);
        String pkPhone = shOrder.getString(KEY_PK_PHONE, null);
        String pkComment = shOrder.getString(KEY_PK_COMMENT, null);

        String receiver = shOrder.getString(KEY_RECEIVER, null);
        String rcvAddress = shOrder.getString(KEY_RCV_ADDRESS, null);
        String rcvDateTime = shOrder.getString(KEY_RCV_DATE_TIME, null);
        String rcvPhone = shOrder.getString(KEY_RCV_PHONE, null);
        String rcvComment = shOrder.getString(KEY_RCV_COMMENT, null);

        String price = shOrder.getString(KEY_PRICE,null);

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

        tvFinalPrice = findViewById(R.id.tvFinalPrice);
        btSubmitOrder = findViewById(R.id.btSubmitOrder);


        tvItem.setText("What's in it: "+item);
        tvWeight.setText("Up to "+weight+" kg, Book a Courier");
        tvParcelValue.setText("Stated value: Rs. "+parVal);

        tvSender.setText(sender);
        tvSenderAddress.setText(pkAddress);
        tvPickUpTime.setText(pkDateTime);
        tvSenderPhone.setText(pkPhone);
        tvSenderComments.setText(pkComment);

        tvReceiver.setText(receiver);
        tvReceiverAddress.setText(rcvAddress);
        tvDeliveryTime.setText(rcvDateTime);
        tvReceiverPhone.setText(rcvPhone);
        tvReceiverComments.setText(rcvComment);

        tvFinalPrice.setText(price);


        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubmitOrderActivity.this, NewOrderActivity.class));
            }
        });
        toolbarTitle.setText("Submit Order");
        tvClear.setText("");
        
        btSubmitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                courier();
                order();

//                Toast.makeText(SubmitOrderActivity.this,"Clicked",Toast.LENGTH_SHORT).show();

            }
        });
    }

//    private void courier() {
//        String userId = shOrder.getString(KEY_USER_ID, null);
//        String courierId = shOrder.getString(KEY_COURIER_ID, null);
//
//        ApiService apiService = ApiUtil.getApiService();
//        apiService.createCourier(userId, tvSender.getText().toString(), tvSenderAddress.getText().toString(),
//                tvSenderPhone.getText().toString(), tvPickUpTime.getText().toString(), tvSenderComments.getText().toString(),
//                tvReceiver.getText().toString(), tvReceiverPhone.getText().toString(), tvReceiverAddress.getText().toString(),
//                tvDeliveryTime.getText().toString(), tvReceiverComments.getText().toString()).enqueue(new Callback<CourierResponse>() {
//            @Override
//            public void onResponse(Call<CourierResponse> call, Response<CourierResponse> response) {
//                Log.e("COURIER", "Status code = " + response.code());
//                if (response.code() == 200 || response.code() == 201 ||response.code() == 500) {
////                        try {
////                            Toast.makeText(SignInActivity.this, "" + response.body().string(), Toast.LENGTH_LONG).show();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
//
//                    //save sharedpreference "LOGGED_IN" as true
//                    SharedPreferences.Editor editor = shOrder.edit();
//                    editor.putString(KEY_COURIER_ID, response.body().getData().getId().toString());
//                    editor.putString(KEY_SENDER, response.body().getData().getSenderName());
//                    editor.putString(KEY_PK_ADDRESS, response.body().getData().getSenderAddress());
//                    editor.putString(KEY_PK_PHONE, response.body().getData().getSenderPhone());
//                    editor.putString(KEY_PK_DATE_TIME, response.body().getData().getCourierPickupdate());
//                    editor.putString(KEY_PK_COMMENT, response.body().getData().getSenderComment());
//
//                    editor.putString(KEY_RECEIVER, response.body().getData().getRecipientName());
//                    editor.putString(KEY_RCV_PHONE, response.body().getData().getRecipientPhone());
//                    editor.putString(KEY_RCV_ADDRESS, response.body().getData().getRecipientAddress());
//                    editor.putString(KEY_RCV_DATE_TIME, response.body().getData().getCourierDeliverydate());
//                    editor.putString(KEY_RCV_COMMENT, response.body().getData().getRecipientComment());
//                    editor.apply();
//
////                    startActivity(new Intent(SubmitOrderActivity.this, HomeActivity.class));
//
////                    finish();
////                    startActivity(getIntent());
//                } else {
//                    Toast.makeText(SubmitOrderActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CourierResponse> call, Throwable t) {
//                Toast.makeText(SubmitOrderActivity.this, "On Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void order() {
        String userId = shOrder.getString(KEY_USER_ID, null);
        String courierId = shOrder.getString(KEY_COURIER_ID, null);

        String item = shOrder.getString(KEY_ITEM, null);
        String quantity = shOrder.getString(KEY_QUANTITY, null);
        String weight = shOrder.getString(KEY_WEIGHT, null);
        String parVal = shOrder.getString(KEY_PAR_VAL, null);

        ApiService apiService = ApiUtil.getApiService();
        apiService.createOrder(userId, courierId, item,
                quantity,
                parVal, weight,
                tvFinalPrice.getText().toString()).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                Log.e("ORDER", "Status code = " + response.code());

                try {
                    Log.v("TAGESTO", response.errorBody().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (response.code() == 200 || response.code() == 201) {
//                        try {
//                            Toast.makeText(SignInActivity.this, "" + response.body().string(), Toast.LENGTH_LONG).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                    //save sharedpreference "LOGGED_IN" as true
//                    SharedPreferences.Editor editor = shOrder.edit();
////                    editor.putString(KEY_COURIER_ID, response.body().getData().getId().toString());
//                    editor.putString(KEY_SENDER, response.body().getData().getSenderName());
//                    editor.putString(KEY_PK_ADDRESS, response.body().getData().getSenderAddress());
//                    editor.putString(KEY_PK_PHONE, response.body().getData().getSenderPhone());
//                    editor.putString(KEY_PK_DATE_TIME, response.body().getData().getCourierPickupdate());
//                    editor.putString(KEY_PK_COMMENT, response.body().getData().getSenderComment());
//
//                    editor.putString(KEY_RECEIVER, response.body().getData().getRecipientName());
//                    editor.putString(KEY_RCV_PHONE, response.body().getData().getRecipientPhone());
//                    editor.putString(KEY_RCV_ADDRESS, response.body().getData().getRecipientAddress());
//                    editor.putString(KEY_RCV_DATE_TIME, response.body().getData().getCourierDeliverydate());
//                    editor.putString(KEY_RCV_COMMENT, response.body().getData().getRecipientComment());
//
//                    editor.apply();

                    Toast.makeText(SubmitOrderActivity.this, "Order successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SubmitOrderActivity.this, HomeActivity.class));

                    finishAffinity();
//                    finish();
//                    startActivity(getIntent());
                } else {
                    Toast.makeText(SubmitOrderActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Toast.makeText(SubmitOrderActivity.this, "On Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}