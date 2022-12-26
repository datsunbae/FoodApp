package com.android.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodapp.Models.Food;
import com.android.foodapp.R;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

public class Food_Admin_Adapter extends RecyclerView.Adapter<Food_Admin_Adapter.ViewHolder> {
    private final List<Food> listFood;
    public Food_Admin_Adapter(List<Food> listFoods){
        listFood = listFoods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View foodView = inflater.inflate(R.layout.item_admin_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(foodView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = listFood.get(position);
        Context context = holder.imageView.getContext();
        holder.tvNameProduct.setText(food.getNameFood());
        holder.tvPrice.setText(String.valueOf(food.getPrice()));

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        if(food.getPercentPromotion() == 0){
            int getPrice = (int) food.getPrice();
            String price = formatter.format(getPrice);
            holder.tvPrice.setText(price);
        }
        else{
            double getPrice = food.getPrice();
            String price = formatter.format(getPrice);
            double getPriceDiscount = food.getPrice() - ((food.getPrice()*food.getPercentPromotion()) /100);
            String priceDiscount = formatter.format(Math.round(getPriceDiscount/1000)*1000);
            holder.tvPrice.setText(priceDiscount);
        }

        Glide.with(context).load(food.getImg()).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvNameProduct, tvPrice;
        public ViewHolder(@NonNull View view){
            super(view);
            imageView = view.findViewById(R.id.imgImageFood_Admin);
            tvNameProduct = view.findViewById(R.id.txtNameFood_Admin);
            tvPrice = view.findViewById(R.id.txtPriceProduct_Admin);
        }
    }
}
