package com.example.courierms.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courierms.OrderDetailsActivity;
import com.example.courierms.R;
import com.example.courierms.Remote.ApiService;
import com.example.courierms.Remote.ApiUtil;
import com.example.courierms.adapter.ItemAdapter;
import com.example.courierms.model.Datum;
import com.example.courierms.model.HistoryResponse;
import com.example.courierms.model.Model;
import com.example.courierms.model.OrderDetailsResponse;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment implements ItemAdapter.OnOrderListener {

    ImageView btBack;
    TextView toolbarTitle;
    TextView tvClear;

//    RecyclerView recyclerView;
//    LinearLayoutManager linearLayoutManager;
//    ItemAdapter adapter;
    List<Model> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        btBack = (ImageView) getView().findViewById(R.id.btBack);
        toolbarTitle = (TextView) getView().findViewById(R.id.toolbarTitle);
        tvClear = (TextView) getView().findViewById(R.id.tvClear);

        btBack.setAlpha(0);
        toolbarTitle.setText("History");
        tvClear.setText("");

//        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //initData();

//        recyclerView.setAdapter(new ItemAdapter(initData()));



        showOrderList();

    }

//    private List<Model> initData() {
//
//        itemList = new ArrayList<>();
//        itemList.add(new Model("Item: Books","Delivered on: On process","Chitwan","Muglin","KTM","Delivered by: BA 3 KHA 184","Cost: Rs. 1000/-"));
//        itemList.add(new Model("Item: Flowers","Delivered on: 2077-02-01","Chitwan","Muglin","Pokhara","Delivered by: BA 3 KHA 185","Cost: Rs. 1500/-"));
//        itemList.add(new Model("Item: Clothes","Delivered on: On process","Chitwan","Muglin","KTM","Delivered by: BA 3 KHA 186","Cost: Rs. 1000/-"));
//        itemList.add(new Model("Item: Drinks","Delivered on: On process","Chitwan","Muglin","KTM","Delivered by: BA 3 KHA 186","Cost: Rs. 1000/-"));
//        itemList.add(new Model("Item: Clothes","Delivered on: On process","Chitwan","Muglin","KTM","Delivered by: BA 3 KHA 186","Cost: Rs. 1000/-"));
//
//        return itemList;
//    }

    private void orderRecyclerView(List<Model> historyResponse) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView = getView().findViewById(R.id.recycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        ItemAdapter adapter = new ItemAdapter(historyResponse, this, getContext());
        recyclerView.setAdapter(adapter);
    }

    public void showOrderList() {
        SharedPreferences sh = this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final String KEY_USER_ID = "userId";
        String userId = sh.getString(KEY_USER_ID, null);
        ApiService apiService = ApiUtil.getApiService();
        apiService.showOrder(userId).enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                Log.e("HISTORYFRAGMENT","status code =" +response.code());
                Log.v("USERID",""+userId);
                HistoryResponse historyResponse=response.body();
//                try {
//                    Log.v("TAGESTO", response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                if (response.code() == 200 || response.code() == 201) {
//
//                    itemList = response.body();
////                    ItemAdapter adapter = new ItemAdapter(itemList, getContext());
////                    recyclerView.setAdapter(adapter);
//                    setUpRecyclerView(itemList);
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
//                        Toast.makeText(getActivity(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }

                orderRecyclerView(historyResponse.getData());
            }


            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {

                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onOrderClick(int position) {
        Log.d("TAG", "onOrderClick: clicked. " + position);

        Intent i = new Intent(getContext(), OrderDetailsActivity.class);
//        i.putExtra("courierId", itemList.get(position).getCourier_id());
        startActivity(i);
    }

//    private void setUpRecyclerView (List<Model> itemList) {
//        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        adapter = new ItemAdapter(itemList, getContext());
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//    }

}
