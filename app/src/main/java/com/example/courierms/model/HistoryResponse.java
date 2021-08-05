package com.example.courierms.model;

import java.util.List;

public class HistoryResponse {
    private boolean success;
    private List<Model> data;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Model> getData() {
        return data;
    }

    public void setData(List<Model> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HistoryResponse(boolean success, List<Model> data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
}
