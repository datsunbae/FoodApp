package com.android.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Fragment.CartFragment;
import com.android.foodapp.Helper.Helper;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Cart;
import com.android.foodapp.Models.Food;
import com.android.foodapp.Models.HandlerCart;
import com.android.foodapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    TextView tvNameFood, tvPrice, tvTotalPrice, tvDescription, tvQuantity, btnAddCard;
    ImageView imgvImgFood, btnPlus, btnMinus;
    HandlerCart handlerCart;
    Helper helper;
    int quantity = 1;
    double priceCartNew = 0;
    Food getFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailproduct);
        tvNameFood = findViewById(R.id.nameFood_activityDetailsProduct);
        tvPrice = findViewById(R.id.txtPrice_activityDetailsProduct);
        tvTotalPrice = findViewById(R.id.txtTotalPrice_activityDetailsProduct);
        tvDescription = findViewById(R.id.txtDes_activityDetailsProduct);
        tvQuantity = findViewById(R.id.txtNumber_activityDetailsProduct);
        btnAddCard = findViewById(R.id.btnAddCard_activityDetailsProduct);
        imgvImgFood = findViewById(R.id.img_activityDetailsProduct);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        int idFood = (int) getIntent().getExtras().get("food_id");
        GetDetailFood(idFood);


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = Integer.parseInt(tvQuantity.getText().toString()) + 1;
                tvQuantity.setText(String.valueOf(quantity));
                Toast.makeText(DetailActivity.this, String.valueOf(quantity), Toast.LENGTH_SHORT).show();
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = Integer.parseInt(tvQuantity.getText().toString());
                if(quantity != 1){
                    tvQuantity.setText(String.valueOf(quantity--));
                }
            }
        });


        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart = new Cart();
                cart.setQuantity(quantity);
                cart.setIdFood(getFood.getIdFood());
                cart.setImgFood(getFood.getImg());
                cart.setPrice(priceCartNew);
                cart.setNameFood(getFood.getNameFood());
                handlerCart = new HandlerCart();
                handlerCart.AddCart(cart);
                CartFragment.cartFragment.refreshCart(handlerCart.getCart());
            }
        });
    }

    private void GetDetailFood(int id){
        try {
            Call call = RetrofitClient.getInstance().getMyApi().GetDetailFood(id);
            call.enqueue(new Callback<Food>() {
                @Override
                public void onResponse(Call<Food> call, Response<Food> response) {
                    Food food = response.body();

                    if(food == null){
                        finish();
                    }
                    else{
                        getFood = food;
                        tvNameFood.setText(food.getNameFood());

                        tvDescription.setText(food.getDescription());
                        Context context = imgvImgFood.getContext();
                        Glide.with(context).load(food.getImg()).placeholder(R.drawable.ic_launcher_background).into(imgvImgFood);

                        helper = new Helper();

                        if(food.getPercentPromotion() == 0){
                            double getPrice =  food.getPrice();
                            priceCartNew = getPrice;
                            tvTotalPrice.setText(helper.FormatPrice(getPrice));
                        }
                        else{
                            double getPrice = food.getPrice();
                            double getPriceDiscount = food.getPrice() - ((food.getPrice()*food.getPercentPromotion()) /100);
                            priceCartNew = Math.round(getPriceDiscount/1000)*1000;
                            tvTotalPrice.setText(helper.FormatPrice(priceCartNew));
                        }
                    }
                }
                @Override
                public void onFailure(Call<Food> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(DetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }
}