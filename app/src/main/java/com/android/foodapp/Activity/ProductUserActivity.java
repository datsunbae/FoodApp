package com.android.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.foodapp.Adapter.FoodAdapter;
import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Food;
import com.android.foodapp.Models.Message;
import com.android.foodapp.Models.OrderBill;
import com.android.foodapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductUserActivity extends AppCompatActivity {

    public FoodAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_product);
        recyclerView = findViewById(R.id.rcvProduct_User);

        ShowProducts();
    }

    private void ShowProducts(){
        try {
            Call call = RetrofitClient.getInstance().getMyApi().GetAllFood();
            call.enqueue(new Callback<List<Food>>() {
                @Override
                public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                    List<Food> listFoods = response.body();
                    adapter = new FoodAdapter(listFoods);
                    RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ProductUserActivity.this, DividerItemDecoration.VERTICAL);
                    recyclerView.addItemDecoration(itemDecoration);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductUserActivity.this));
                    recyclerView.setAdapter(adapter);
                }
                @Override
                public void onFailure(Call<List<Food>> call, Throwable t) {
                    Toast.makeText(ProductUserActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(ProductUserActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }



}