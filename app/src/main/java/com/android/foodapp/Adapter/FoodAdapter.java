package com.android.foodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private List<Food> listFood;
    public FoodAdapter(List<Food> listFoods){
        listFood = listFoods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View foodView = inflater.inflate(R.layout.item_user_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(foodView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = listFood.get(position);
        Context context = holder.imageView.getContext();
        holder.tvNameProduct.setText(food.getNameFood());
        holder.tvPrice.setText(String.valueOf(food.getPrice()));
        double priceCartNew = 0;

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        if(food.getPercentPromotion() == 0){
            double getPrice =  food.getPrice();
            String price = formatter.format(getPrice);
            priceCartNew = getPrice;

            holder.tvPrice1.setVisibility(View.GONE);
            holder.constraintLayoutDiscount.setVisibility(View.GONE);
            holder.tvPrice.setText(price);
        }
        else{
            double getPrice = food.getPrice();
            String price = formatter.format(getPrice);
            double getPriceDiscount = food.getPrice() - ((food.getPrice()*food.getPercentPromotion()) /100);
            String priceDiscount = formatter.format(Math.round(getPriceDiscount/1000)*1000);
            priceCartNew = Math.round(getPriceDiscount/1000)*1000;

            holder.txtDiscountPercent.setText(String.valueOf(food.getPercentPromotion()) + "%");
            holder.tvPrice.setText(priceDiscount);
            holder.tvPrice1.setText(price);
        }

        Glide.with(context).load(food.getImg()).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);

        double finalPriceCartNew = priceCartNew;
        holder.btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart = new Cart();
                cart.setQuantity(1);
                cart.setIdFood(food.getIdFood());
                cart.setImgFood(food.getImg());
                cart.setPrice(finalPriceCartNew);
                cart.setNameFood(food.getNameFood());
                HandlerCart handlerCart = new HandlerCart();
                handlerCart.AddCart(cart);
                List<Cart> newListCart = handlerCart.getCart();
                CartFragment cartFragment = CartFragment.cartFragment;
                cartFragment.refreshCart(newListCart);
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
        ImageView imageView;
        TextView tvNameProduct;
        TextView tvPrice, tvPrice1, txtDiscountPercent, txtEmpty;
        ScrollView scrollViewCart;
        ConstraintLayout btnAddCard, constraintLayoutDiscount;
        public ViewHolder(@NonNull View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imgImage_Product);
            tvNameProduct = (TextView) view.findViewById(R.id.txtName_Product);
            tvPrice = (TextView) view.findViewById(R.id.txtPrice_Product);
            tvPrice1 = (TextView) view.findViewById(R.id.txtPrice1_Product);
            btnAddCard = (ConstraintLayout) view.findViewById(R.id.btnAddCard);
            txtDiscountPercent = (TextView) view.findViewById(R.id.txtDiscountPercent_Product);
            constraintLayoutDiscount = (ConstraintLayout) view.findViewById(R.id.constraintDiscount_Product);

            txtEmpty = (TextView) view.findViewById(R.id.txtEmpty);
            scrollViewCart = (ScrollView) view.findViewById(R.id.scrollViewCart);
        }
    }

    public void filterList(List<Food> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        listFood = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }


}
