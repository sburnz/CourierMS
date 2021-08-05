package com.example.courierms.model;

public class CourierModel {

//    String item;
//    String status;
//    String startAdd;
//    String station1;
//    String endAdd;
//    String deliveredBy;
//    String cost;
//
//    public Model(String item, String status, String startAdd, String station1, String endAdd, String deliveredBy, String cost) {
//        this.item = item;
//        this.status = status;
//        this.startAdd = startAdd;
//        this.station1 = station1;
//        this.endAdd = endAdd;
//        this.deliveredBy = deliveredBy;
//        this.cost = cost;
//    }
//
//    public String getItem() {
//        return item;
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getStartAdd() {
//        return startAdd;
//    }
//
//    public void setStartAdd(String startAdd) {
//        this.startAdd = startAdd;
//    }
//
//    public String getStation1() {
//        return station1;
//    }
//
//    public void setStation1(String station1) {
//        this.station1 = station1;
//    }
//
//    public String getEndAdd() {
//        return endAdd;
//    }
//
//    public void setEndAdd(String endAdd) {
//        this.endAdd = endAdd;
//    }
//
//    public String getDeliveredBy() {
//        return deliveredBy;
//    }
//
//    public void setDeliveredBy(String deliveredBy) {
//        this.deliveredBy = deliveredBy;
//    }
//
//    public String getCost() {
//        return cost;
//    }
//
//    public void setCost(String cost) {
//        this.cost = cost;
//    }

    private int id;
    private int user_id;
    private String Sender_name;
    private String Sender_address;
    private String Sender_phone;
    private String Courier_pickupdate;
    private String Sender_comment;
    private String Recipient_name;
    private String Recipient_phone;
    private String Recipient_address;
    private String Courier_deliverydate;
    private String Recipient_comment;

    public CourierModel(int id, int user_id, String sender_name, String sender_address, String sender_phone, String courier_pickupdate, String sender_comment, String recipient_name, String recipient_phone, String recipient_address, String courier_deliverydate, String recipient_comment) {
        this.id = id;
        this.user_id = user_id;
        Sender_name = sender_name;
        Sender_address = sender_address;
        Sender_phone = sender_phone;
        Courier_pickupdate = courier_pickupdate;
        Sender_comment = sender_comment;
        Recipient_name = recipient_name;
        Recipient_phone = recipient_phone;
        Recipient_address = recipient_address;
        Courier_deliverydate = courier_deliverydate;
        Recipient_comment = recipient_comment;
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

    public String getSender_name() {
        return Sender_name;
    }

    public void setSender_name(String sender_name) {
        Sender_name = sender_name;
    }

    public String getSender_address() {
        return Sender_address;
    }

    public void setSender_address(String sender_address) {
        Sender_address = sender_address;
    }

    public String getSender_phone() {
        return Sender_phone;
    }

    public void setSender_phone(String sender_phone) {
        Sender_phone = sender_phone;
    }

    public String getCourier_pickupdate() {
        return Courier_pickupdate;
    }

    public void setCourier_pickupdate(String courier_pickupdate) {
        Courier_pickupdate = courier_pickupdate;
    }

    public String getSender_comment() {
        return Sender_comment;
    }

    public void setSender_comment(String sender_comment) {
        Sender_comment = sender_comment;
    }

    public String getRecipient_name() {
        return Recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        Recipient_name = recipient_name;
    }

    public String getRecipient_phone() {
        return Recipient_phone;
    }

    public void setRecipient_phone(String recipient_phone) {
        Recipient_phone = recipient_phone;
    }

    public String getRecipient_address() {
        return Recipient_address;
    }

    public void setRecipient_address(String recipient_address) {
        Recipient_address = recipient_address;
    }

    public String getCourier_deliverydate() {
        return Courier_deliverydate;
    }

    public void setCourier_deliverydate(String courier_deliverydate) {
        Courier_deliverydate = courier_deliverydate;
    }

    public String getRecipient_comment() {
        return Recipient_comment;
    }

    public void setRecipient_comment(String recipient_comment) {
        Recipient_comment = recipient_comment;
    }
}
