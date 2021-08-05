package com.example.courierms.model;

import java.util.List;

public class DetailedHistoryResponse {
    private boolean success;
    private List<CourierModel> data;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<CourierModel> getData() {
        return data;
    }

    public void setData(List<CourierModel> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DetailedHistoryResponse(boolean success, List<CourierModel> data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
}
