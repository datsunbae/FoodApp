package com.android.foodapp.AdminActivity.Product;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodapp.Adapter.Food_Admin_Adapter;
import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Models.Food;
import com.android.foodapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdminActivity extends AppCompatActivity {

    public Food_Admin_Adapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        ConstraintLayout btnAdd_Product = findViewById(R.id.btnAdd_Product);
        btnAdd_Product.setOnClickListener(view -> startActivity(new Intent(ProductAdminActivity.this, AddProductAdminActivity.class)));
        recyclerView = findViewById(R.id.rcvProduct_Admin);

        ShowProducts();
    }
    private void ShowProducts() {
        try {
            Call call = RetrofitClient.getInstance().getMyApi().GetAllFood();
            call.enqueue(new Callback<List<Food>>() {
                @Override
                public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                    List<Food> listFoods = response.body();
                    adapter = new Food_Admin_Adapter(listFoods);
                    RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ProductAdminActivity.this, DividerItemDecoration.VERTICAL);
                    recyclerView.addItemDecoration(itemDecoration);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductAdminActivity.this));
                    recyclerView.setAdapter(adapter);
                }
                @Override
                public void onFailure(Call<List<Food>> call, Throwable t) {
                    Toast.makeText(ProductAdminActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(ProductAdminActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }
}