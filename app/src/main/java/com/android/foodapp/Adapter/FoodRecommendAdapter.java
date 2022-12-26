package com.android.foodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodapp.Activity.DetailActivity;
import com.android.foodapp.Fragment.CartFragment;
import com.android.foodapp.Models.Cart;
import com.android.foodapp.Models.Food;
import com.android.foodapp.Models.HandlerCart;
import com.android.foodapp.R;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

public class FoodRecommendAdapter extends RecyclerView.Adapter<FoodRecommendAdapter.ViewHolder>{
    private List<Food> listFood;
    double priceCart = 0;
    public FoodRecommendAdapter(List<Food> listFoods){
        listFood = listFoods;
    }

    @NonNull
    @Override
    public FoodRecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View foodView = inflater.inflate(R.layout.item_user_recommended, parent, false);
        FoodRecommendAdapter.ViewHolder viewHolder = new FoodRecommendAdapter.ViewHolder(foodView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecommendAdapter.ViewHolder holder, int position) {
        Food food = listFood.get(position);
        Context context = holder.imageView.getContext();
        holder.tvNameProduct.setText(food.getNameFood());

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        if(food.getPercentPromotion() == 0){
            int getPrice = (int) food.getPrice();
            String price = formatter.format(getPrice);
            priceCart = getPrice;

            holder.tvPrice1.setVisibility(View.GONE);
            holder.discountConstraintLayout.setVisibility(View.GONE);
            holder.tvPrice.setText(price);
        }
        else{
            double getPrice = food.getPrice();
            String price = formatter.format(getPrice);
            double getPriceDiscount = food.getPrice() - ((food.getPrice()*food.getPercentPromotion()) /100);
            String priceDiscount = formatter.format(Math.round(getPriceDiscount/1000)*1000);
            priceCart = Math.round(getPriceDiscount/1000)*1000;
            holder.tvDiscountPercent.setText(String.valueOf(food.getPercentPromotion()) + "%");
            holder.tvPrice.setText(priceDiscount);
            holder.tvPrice1.setText(price);
        }


        Glide.with(context).load(food.getImg()).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);


        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart = new Cart();
                cart.setQuantity(1);
                cart.setIdFood(food.getIdFood());
                cart.setImgFood(food.getImg());
                cart.setPrice(priceCart);
                cart.setNameFood(food.getNameFood());
                HandlerCart handlerCart = new HandlerCart();
                handlerCart.AddCart(cart);
                CartFragment.cartFragment.refreshCart(handlerCart.getCart());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("food_id", food.getIdFood());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView, btnAdd;
        TextView tvNameProduct, tvPrice, tvDiscountPercent, tvPrice1;
        ConstraintLayout discountConstraintLayout;
        public ViewHolder(@NonNull View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imgFood);
            tvNameProduct = (TextView) view.findViewById(R.id.txtFood);
            tvDiscountPercent = (TextView) view.findViewById(R.id.discountPercent_itemUserRecommend);
            tvPrice = (TextView) view.findViewById(R.id.txtPrice);
            tvPrice1 = (TextView) view.findViewById(R.id.txtPrice1);
            btnAdd = (ImageView) view.findViewById(R.id.btnAddCard_itemRecommend);
            discountConstraintLayout = (ConstraintLayout) view.findViewById(R.id.discount_itemUserRecommend);
        }
    }
}
