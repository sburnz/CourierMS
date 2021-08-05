package com.example.courierms.model;

public class Model {

    private int id;
    private int user_id;
    private int courier_id;
    private String goods_desc;
    private int Quantity;
    private String Parcle_value;
    private int weight;
    private String price;

    public Model(int id, int user_id, int courier_id, String goods_desc, int quantity, String parcle_value, int weight, String price) {
        this.id = id;
        this.user_id = user_id;
        this.courier_id = courier_id;
        this.goods_desc = goods_desc;
        Quantity = quantity;
        Parcle_value = parcle_value;
        this.weight = weight;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(int courier_id) {
        this.courier_id = courier_id;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getParcle_value() {
        return Parcle_value;
    }

    public void setParcle_value(String parcle_value) {
        Parcle_value = parcle_value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
