package com.example.courierms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courierms.Remote.ApiService;
import com.example.courierms.Remote.ApiUtil;
import com.example.courierms.adapter.ItemAdapter;
import com.example.courierms.model.HistoryResponse;
import com.example.courierms.model.Model;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity implements ItemAdapter.OnOrderListener {

    ImageView btBack;
    TextView toolbarTitle;
    TextView tvClear;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ItemAdapter adapter;
    List<Model> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history2);

        btBack = findViewById(R.id.btBack);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        tvClear = findViewById(R.id.tvClear);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoryActivity.this, HomeActivity.class));
            }
        });
        toolbarTitle.setText("History");
        tvClear.setText("");

        showOrderList();
    }

    private void orderRecyclerView(List<Model> historyResponse) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        ItemAdapter adapter = new ItemAdapter(historyResponse, this, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    private void showOrderList() {
        SharedPreferences sh = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final String KEY_USER_ID = "userId";
        String userId = sh.getString(KEY_USER_ID, null);
        ApiService apiService = ApiUtil.getApiService();
        apiService.showOrder(userId).enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                Log.e("HISTORYACTIVITY","status code =" +response.code());
                Log.v("USERID",""+userId);
                HistoryResponse historyResponse=response.body();
//                if (response.code() == 200 || response.code() == 201) {
//
//                    itemList = response.body();
////                    ItemAdapter adapter = new ItemAdapter(itemList, getContext());
////                    recyclerView.setAdapter(adapter);
//                    setUpRecyclerView(itemList.getData());
//
////                List<ItemModel>itemList =response.body();
//
////                ItemAdapter adapter= new ItemAdapter(itemList);
//
////                for (ItemModel itemModel:itemList){
////                    int k =itemModel.getBd_id();
////                    String price = itemModel.getPrice();
//
////                    itemList.add(itemModel);
////                }
//
//                } else {
//                    try {
//                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }

                orderRecyclerView(historyResponse.getData());
            }


            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onOrderClick(int position) {
        Log.d("TAG", "onOrderClick: clicked. " + position);

        Intent i = new Intent(this, OrderDetailsActivity.class);
//        i.putExtra("courierId", itemList.get(position).getCourier_id());
        this.startActivity(i);
    }

//    private void setUpRecyclerView (List<Model> itemList) {
//        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        adapter = new ItemAdapter(itemList, getApplicationContext());
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//    }

}