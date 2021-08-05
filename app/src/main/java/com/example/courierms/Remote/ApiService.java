package com.example.courierms.Remote;

import com.example.courierms.model.CourierResponse;
import com.example.courierms.model.DetailedHistoryResponse;
import com.example.courierms.model.HistoryResponse;
import com.example.courierms.model.LoginResponse;
import com.example.courierms.model.Model;
import com.example.courierms.model.OrderResponse;
import com.example.courierms.model.RegisterResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("school")
    Call<ResponseBody> getSchoolList();

    @POST("validateUser")
    Call<ResponseBody> validateUserLogin(@Query("id") String id, @Query("password") String password);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("verify")
    Call<ResponseBody> userVerify(@Field("otp_code") String otp_code);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> userRegister(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("create/courier")
    Call<CourierResponse> createCourier(@Field("user_id") String userId,
                                        @Field("Sender_name") String sender,
                                        @Field("Sender_address") String pkAddress,
                                        @Field("Sender_phone") String pkPhone,
                                        @Field("Courier_pickupdate") String pkDateTime,
                                        @Field("Sender_comment") String pkComment,
                                        @Field("Recipient_name") String receiver,
                                        @Field("Recipient_phone") String rcvPhone,
                                        @Field("Recipient_address") String rcvAddress,
                                        @Field("Courier_deliverydate") String rcvDateTime,
                                        @Field("Recipient_comment") String rcvComment);

    @FormUrlEncoded
    @POST("create/order")
    Call<OrderResponse> createOrder(@Field("user_id") String userId,
                                    @Field("courier_id") String courierId,
                                    @Field("goods_desc") String item,
                                    @Field("Quantity") String quantity,
                                    @Field("Parcle_value") String parVal,
                                    @Field("weight") String weight,
                                    @Field("price") String price);

    @GET("show/orderdetails/{user_id}")
    Call<HistoryResponse> showOrder(@Path("user_id") String userId);

    @GET("show/courierdetails/{user_id}")
    Call<DetailedHistoryResponse> showCourier(@Path("user_id") String userId);
}
