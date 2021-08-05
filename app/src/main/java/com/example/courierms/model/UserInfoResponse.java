package com.example.courierms.model;

import java.util.List;

public class UserInfoResponse {
    private List<UserInfoItem> userInfo;
    private int statusCode;
    private String statusMessage;

    public UserInfoResponse(List<UserInfoItem> userInfo, int statusCode, String statusMessage) {
        this.userInfo = userInfo;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public List<UserInfoItem> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfoItem> userInfo) {
        this.userInfo = userInfo;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
