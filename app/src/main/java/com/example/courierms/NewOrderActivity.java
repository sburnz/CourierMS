package com.example.courierms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.courierms.Remote.ApiService;
import com.example.courierms.Remote.ApiUtil;
import com.example.courierms.model.CourierResponse;
import com.example.courierms.model.UserInfoResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewOrderActivity extends AppCompatActivity {

    ImageView btBack;
    TextView toolbarTitle;
    TextView tvClear;

    AutoCompleteTextView dropdownWeight;
    TextInputLayout dropdownWeightLayout;
    EditText etSender;
    EditText etPkAddress;
    TextInputLayout etPkAddressLayout;
    EditText etPkPhone;
    private static final int PICK_CONTACT = 1;
    TextInputLayout etPkPhoneLayout;
    EditText etPkDateTime;
    EditText etPkComment;

    EditText etReceiver;
    EditText etRcvAddress;
    EditText etRcvPhone;
    EditText etRcvDateTime;
    EditText etRcvComment;

    EditText etItem;
    EditText etQuantity;
    EditText etParVal;

    RadioGroup radioGroup;

    TextView tvPrice;

    Button btCreateOrder;

    FusedLocationProviderClient fusedLocationProviderClient;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

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

        String price = shOrder.getString(KEY_PRICE, null);

        btBack = findViewById(R.id.btBack);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        tvClear = findViewById(R.id.tvClear);

        dropdownWeight = findViewById(R.id.dropdownWeight);
        dropdownWeightLayout = findViewById(R.id.dropdownWeightLayout);
        etSender = findViewById(R.id.etSender);
        etPkAddress = findViewById(R.id.etPkAddress);
        etPkAddressLayout = findViewById(R.id.etPkAddressLayout);
        etPkPhone = findViewById(R.id.etPkPhone);
        etPkPhoneLayout = findViewById(R.id.etPkPhoneLayout);
        etPkDateTime = findViewById(R.id.etPkDateTime);
        etPkComment = findViewById(R.id.etPkComment);

        etPkDateTime.setInputType(InputType.TYPE_NULL);

        etReceiver = findViewById(R.id.etReceiver);
        etRcvAddress = findViewById(R.id.etRcvAddress);
        etRcvPhone = findViewById(R.id.etRcvPhone);
        etRcvDateTime = findViewById(R.id.etRcvDateTime);
        etRcvComment = findViewById(R.id.etRcvComment);

        etRcvDateTime.setInputType(InputType.TYPE_NULL);

        etItem = findViewById(R.id.etItem);
        etQuantity = findViewById(R.id.etQuantity);
        etParVal = findViewById(R.id.etParVal);

        final RadioGroup radioGroup = findViewById(R.id.radioGroup);

        tvPrice = findViewById(R.id.tvPrice);

        btCreateOrder = findViewById(R.id.btCreateOrder);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewOrderActivity.this, SignInActivity.class));
            }
        });
        toolbarTitle.setText("New Order type");
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(NewOrderActivity.this)
                        .setTitle("Clear form?")
                        .setMessage("All entered data will be lost.")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dropdownWeight.clearListSelection();
                                etSender.setText("");
                                etPkAddress.setText("");
                                etPkPhone.setText("");
                                etPkDateTime.setText("");
                                etPkComment.setText("");
                                etReceiver.setText("");
                                etRcvAddress.setText("");
                                etRcvPhone.setText("");
                                etRcvDateTime.setText("");
                                etRcvComment.setText("");
                                etItem.setText("");
                                etQuantity.setText("");
                                etParVal.setText("");
                                radioGroup.clearCheck();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        final String[] option = {"1", "5", "10", "15", "20"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option_item, option);

        //To make default value
        dropdownWeight.setText(arrayAdapter.getItem(0).toString(), false);
        dropdownWeight.setAdapter(arrayAdapter);

        tvPrice.setText("Rs. 30");


        dropdownWeight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0) {
                    tvPrice.setText("Rs. 30");
                } else if (position == 1) {
                    tvPrice.setText("Rs. 60");
                } else if (position == 2) {
                    tvPrice.setText("Rs. 90");
                } else if (position == 3) {
                    tvPrice.setText("Rs. 120");
                } else if (position == 4) {
                    tvPrice.setText("Rs. 150");
                }
            }
        });

        etPkAddressLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check permission
                if (ActivityCompat.checkSelfPermission(NewOrderActivity.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //When permission granted
                    getLocation();
                } else {
                    //When permission denied
                    ActivityCompat.requestPermissions(NewOrderActivity.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });


        etPkPhoneLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });


        //working on editText of Pickup date and time
        etPkDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(etPkDateTime);
            }
        });

        //working on editText of Delivery date and time
        etRcvDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(etRcvDateTime);
            }
        });

        btCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences.Editor editor = shOrder.edit();
//                editor.putString(KEY_ITEM, etItem.getText().toString());
//                editor.putString(KEY_WEIGHT, dropdownWeight.getText().toString());
//                editor.putString(KEY_PAR_VAL, etParVal.getText().toString());
//
//                editor.putString(KEY_SENDER, etSender.getText().toString());
//                editor.putString(KEY_PK_ADDRESS, etPkAddress.getText().toString());
//                editor.putString(KEY_PK_DATE_TIME, etPkDateTime.getText().toString());
//                editor.putString(KEY_PK_PHONE, etPkPhone.getText().toString());
//                editor.putString(KEY_PK_COMMENT, etPkComment.getText().toString());
//
//                editor.putString(KEY_RECEIVER, etReceiver.getText().toString());
//                editor.putString(KEY_RCV_ADDRESS, etRcvAddress.getText().toString());
//                editor.putString(KEY_RCV_DATE_TIME, etRcvDateTime.getText().toString());
//                editor.putString(KEY_RCV_PHONE, etRcvPhone.getText().toString());
//                editor.putString(KEY_RCV_COMMENT, etRcvComment.getText().toString());
//
//                editor.putString(KEY_PRICE, tvPrice.getText().toString());
//                editor.apply();
//
//
//                startActivity(new Intent(NewOrderActivity.this, SubmitOrderActivity.class));

                courier();


            }
        });
    }

    private void courier() {
        String userId = shOrder.getString(KEY_USER_ID, null);
//        String courierId = shOrder.getString(KEY_COURIER_ID, null);

        String serverPkDate = "";
        String serverRcvDate = "";
//get date format
        String pattern = "dd-MM-yyyy | hh:mm aa";
        DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        serverPkDate = "";
        serverRcvDate = "";
        try {
            Date pkdate = simpleDateFormat.parse(etPkDateTime.getText().toString());
            Date rcvdate = simpleDateFormat.parse(etRcvDateTime.getText().toString());
            //change into correct format
            String sPattern = "yyyy/MM/dd";
            DateFormat serverDateFormat = new SimpleDateFormat(sPattern);
            serverPkDate = serverDateFormat.format(pkdate);
            serverRcvDate = serverDateFormat.format(rcvdate);
        } catch (Exception e) {
            e.printStackTrace();//working on the location
        }

        ApiService apiService = ApiUtil.getApiService();
        apiService.createCourier(userId, etSender.getText().toString(), etPkAddress.getText().toString(),
                etPkPhone.getText().toString(), serverPkDate, etPkComment.getText().toString(),
                etReceiver.getText().toString(), etRcvPhone.getText().toString(), etRcvAddress.getText().toString(),
                serverRcvDate, etRcvComment.getText().toString()).enqueue(new Callback<CourierResponse>() {
            @Override
            public void onResponse(Call<CourierResponse> call, Response<CourierResponse> response) {
                Log.e("COURIER", "Status code = " + response.code());

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
                    SharedPreferences.Editor editor = shOrder.edit();
                    editor.putString(KEY_COURIER_ID, response.body().getData().getId().toString());

                    editor.putString(KEY_ITEM, etItem.getText().toString());
                    editor.putString(KEY_QUANTITY, etQuantity.getText().toString());
                    editor.putString(KEY_WEIGHT, dropdownWeight.getText().toString());
                    editor.putString(KEY_PAR_VAL, etParVal.getText().toString());

                    editor.putString(KEY_SENDER, response.body().getData().getSenderName());
                    editor.putString(KEY_PK_ADDRESS, response.body().getData().getSenderAddress());
                    editor.putString(KEY_PK_PHONE, response.body().getData().getSenderPhone());
                    editor.putString(KEY_PK_DATE_TIME, response.body().getData().getCourierPickupdate());
                    editor.putString(KEY_PK_COMMENT, response.body().getData().getSenderComment());

                    editor.putString(KEY_RECEIVER, response.body().getData().getRecipientName());
                    editor.putString(KEY_RCV_PHONE, response.body().getData().getRecipientPhone());
                    editor.putString(KEY_RCV_ADDRESS, response.body().getData().getRecipientAddress());
                    editor.putString(KEY_RCV_DATE_TIME, response.body().getData().getCourierDeliverydate());
                    editor.putString(KEY_RCV_COMMENT, response.body().getData().getRecipientComment());

                    editor.putString(KEY_PRICE, tvPrice.getText().toString());
                    editor.apply();

                    startActivity(new Intent(NewOrderActivity.this, SubmitOrderActivity.class));

//                    finish();
//                    startActivity(getIntent());
                } else {
                    Toast.makeText(NewOrderActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CourierResponse> call, Throwable t) {
                Toast.makeText(NewOrderActivity.this, "On Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Initialize location
                Location location = task.getResult();
                if (location != null) {
                    try {
                        //Initialize geocoder
                        Geocoder geocoder = new Geocoder(NewOrderActivity.this,
                                Locale.getDefault());
                        //Initialize address list
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        //Set Address
                        etPkAddress.setText(addresses.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(this, "Failed To pick contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor.moveToFirst()) {
                String phoneNo = "";
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String hasPhone = cursor.getString(phoneIndex);

                if (phoneNo.equalsIgnoreCase("1"))
                    hasPhone = "true";
                else
                    hasPhone = "false";

                if (Boolean.parseBoolean(hasPhone)) {
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        phoneNo = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    phones.close();
                }

                etPkPhone.setText(phoneNo);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showDateTimeDialog(final EditText et) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy | hh:mm aa");
                        et.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(NewOrderActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };

       DatePickerDialog datePickerDialog =  new DatePickerDialog(NewOrderActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
       datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
       datePickerDialog.show();
    }
}