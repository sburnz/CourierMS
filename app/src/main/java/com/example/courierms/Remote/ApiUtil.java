package com.example.courierms.Remote;

import retrofit2.Retrofit;

public class ApiUtil {

    public static String baseUrl = "http://courier.oxfordcollege.edu.np/api/";

    public static ApiService getApiService(){
        return RetrofitClient.getRetrofitClient(baseUrl).create(ApiService.class);
    }
}
