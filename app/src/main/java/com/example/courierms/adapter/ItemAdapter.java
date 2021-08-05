package com.example.courierms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courierms.R;
import com.example.courierms.model.HistoryResponse;
import com.example.courierms.model.Model;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    List<Model> itemList;
    Context context;

    private OnOrderListener mOnOrderListener;

    public ItemAdapter(List<Model>itemList, OnOrderListener onOrderListener, Context context) {
        this.context = context;
        this.itemList = (List<Model>) itemList;
        this.mOnOrderListener = onOrderListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view, mOnOrderListener);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {

        holder.itemName.setText("Item: " + itemList.get(position).getGoods_desc());
        holder.quantity.setText("Quantity: " + itemList.get(position).getQuantity());
        holder.parcelValue.setText("Parcel Value: Rs. " + itemList.get(position).getParcle_value());
        holder.weight.setText("Weight: " + itemList.get(position).getWeight() + " kg");
//        holder.status.setText(itemList1.get(position).getStatus());
//        holder.tvStartAdd.setText(itemList1.get(position).getStartAdd());
//        holder.tvStation1Add.setText(itemList1.get(position).getStation1());
//        holder.tvEndAdd.setText(itemList1.get(position).getEndAdd());
//        holder.deliveredBy.setText(itemList1.get(position).getDeliveredBy());
        holder.cost.setText("Price: " + itemList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemName;
        TextView quantity;
        TextView parcelValue;
        TextView weight;
//        TextView tvEndAdd;
//        TextView deliveredBy;
        TextView cost;

        OnOrderListener onOrderListener;

        public ViewHolder(@NonNull View itemView, OnOrderListener onOrderListener) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            quantity = itemView.findViewById(R.id.quantity);
            parcelValue = itemView.findViewById(R.id.parcelValue);
//            tvStation1Add = itemView.findViewById(R.id.tvStation1Add);
//            tvEndAdd = itemView.findViewById(R.id.tvEndAdd);
            weight = itemView.findViewById(R.id.weight);
            cost = itemView.findViewById(R.id.cost);
            this.onOrderListener = onOrderListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onOrderListener.onOrderClick(getAdapterPosition());
        }
    }

    public interface OnOrderListener {
        void onOrderClick(int position);
    }
}