package com.example.courierms.model;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class Odata {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("courier_id")
    @Expose
    private String courierId;
    @SerializedName("goods_desc")
    @Expose
    private String goodsDesc;
    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("Parcle_value")
    @Expose
    private String parcleValue;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getParcleValue() {
        return parcleValue;
    }

    public void setParcleValue(String parcleValue) {
        this.parcleValue = parcleValue;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
