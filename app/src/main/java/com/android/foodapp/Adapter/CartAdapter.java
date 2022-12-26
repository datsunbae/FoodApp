package com.android.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodapp.Fragment.CartFragment;
import com.android.foodapp.Helper.Helper;
import com.android.foodapp.Models.Cart;
import com.android.foodapp.Models.HandlerCart;
import com.android.foodapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    Helper helper;

    private List<Cart> listCart;
    private HandlerCart handlerCart;
    public CartAdapter(List<Cart> listCards){
        listCart = listCards;
        helper = new Helper();
        handlerCart = new HandlerCart();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemCart = inflater.inflate(R.layout.item_user_cart, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemCart);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = listCart.get(position);
        Context context = holder.igmViewFood.getContext();
        holder.nameFood.setText(cart.getNameFood());
        holder.priceFood.setText(String.valueOf(helper.FormatPrice(cart.getPrice())));
        holder.subTotalPrice.setText(String.valueOf(helper.FormatPrice(cart.getSubTotal())));
        holder.quantity.setText(String.valueOf(cart.getQuantity()));
        Glide.with(context).load(cart.getImgFood()).placeholder(R.drawable.ic_launcher_background).into(holder.igmViewFood);

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerCart.UpdateCart(cart.getIdFood(), "plus");
                CartFragment.cartFragment.refreshCart(handlerCart.getCart());
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerCart.UpdateCart(cart.getIdFood(), "minus");
                CartFragment.cartFragment.refreshCart(handlerCart.getCart());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameFood, priceFood, subTotalPrice, quantity;
        ImageView igmViewFood, btnPlus, btnMinus;
        public ViewHolder(@NonNull View view){
            super(view);
            nameFood = view.findViewById(R.id.txtIdOrderBill_itemOrder);
            priceFood = view.findViewById(R.id.txtPrice_itemCart);
            subTotalPrice = view.findViewById(R.id.txtTotalPrice_itemCart);
            quantity = view.findViewById(R.id.txtNumber_itemCart);
            igmViewFood = view.findViewById(R.id.imgImageFoodCart);
            btnPlus = view.findViewById(R.id.btnPlus_itemCart);
            btnMinus = view.findViewById(R.id.btnMinus_itemCart);
        }
    }
}
