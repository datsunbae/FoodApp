package com.android.foodapp.Api;

import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Bill;
import com.android.foodapp.Models.Food;
import com.android.foodapp.Models.ListFood;
import com.android.foodapp.Models.Message;
import com.android.foodapp.Models.OrderBill;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIFoodApp {
    String BASE_URL = "http://10.18.80.73:6969/api/";

    @POST("auth/login")
    Call<Account> Login(@Query("username") String username, @Query("password") String password);

    @POST("auth/Register")
    Call<Message> Register(@Body Account account);

    @PUT("account/EditAccount")
    Call<Message> EditAccount(@Body Account account);

    @GET("food/getallfood")
    Call<List<Food>> GetAllFood();

    @GET("food/GetDetailsFood")
    Call<Food> GetDetailFood(@Query("id") int id);

    @POST("Order/AddOrderBill")
    Call<Message> Order(@Body Bill bill);

    @GET("order/getorders")
    Call<List<OrderBill>> Hehe(@Query("id") int id);
}
