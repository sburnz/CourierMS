package com.example.courierms.model;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class Data {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("Sender_name")
    @Expose
    private String senderName;
    @SerializedName("Sender_address")
    @Expose
    private String senderAddress;
    @SerializedName("Sender_phone")
    @Expose
    private String senderPhone;
    @SerializedName("Courier_pickupdate")
    @Expose
    private String courierPickupdate;
    @SerializedName("Sender_comment")
    @Expose
    private String senderComment;
    @SerializedName("Recipient_name")
    @Expose
    private String recipientName;
    @SerializedName("Recipient_phone")
    @Expose
    private String recipientPhone;
    @SerializedName("Recipient_address")
    @Expose
    private String recipientAddress;
    @SerializedName("Courier_deliverydate")
    @Expose
    private String courierDeliverydate;
    @SerializedName("Recipient_comment")
    @Expose
    private String recipientComment;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getCourierPickupdate() {
        return courierPickupdate;
    }

    public void setCourierPickupdate(String courierPickupdate) {
        this.courierPickupdate = courierPickupdate;
    }

    public String getSenderComment() {
        return senderComment;
    }

    public void setSenderComment(String senderComment) {
        this.senderComment = senderComment;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getCourierDeliverydate() {
        return courierDeliverydate;
    }

    public void setCourierDeliverydate(String courierDeliverydate) {
        this.courierDeliverydate = courierDeliverydate;
    }

    public String getRecipientComment() {
        return recipientComment;
    }

    public void setRecipientComment(String recipientComment) {
        this.recipientComment = recipientComment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
