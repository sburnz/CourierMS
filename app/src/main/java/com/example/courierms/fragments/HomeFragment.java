package com.example.courierms.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.courierms.HistoryActivity;
import com.example.courierms.NewOrderActivity;
import com.example.courierms.OrderDetailsActivity;
import com.example.courierms.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ImageSlider imageSlider;
    CardView cvCreateOrder;
    CardView cvOrderHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        imageSlider = (ImageSlider) getView().findViewById(R.id.slider);
        cvCreateOrder = (CardView) getView().findViewById(R.id.cvCreateOrder);
        cvOrderHistory = (CardView) getView().findViewById(R.id.cvOrderHistory);

        //Code for Image Slider
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.truck_logo));
        slideModels.add(new SlideModel(R.drawable.truck_only));
        slideModels.add(new SlideModel(R.drawable.button_background));

        imageSlider.setImageList(slideModels,true);


        cvCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), NewOrderActivity.class));
            }
        });

        cvOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), HistoryActivity.class));
            }
        });

    }

}
