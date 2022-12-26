package com.android.foodapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.foodapp.Activity.HomeActivity;
import com.android.foodapp.Activity.LoginActivity;
import com.android.foodapp.Activity.MainActivity;
import com.android.foodapp.Activity.ProductUserActivity;
import com.android.foodapp.Activity.RegisterActivity;
import com.android.foodapp.Adapter.CartAdapter;
import com.android.foodapp.Adapter.FoodAdapter;
import com.android.foodapp.Adapter.FoodRecommendAdapter;
import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Helper.Helper;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Bill;
import com.android.foodapp.Models.Cart;
import com.android.foodapp.Models.Food;
import com.android.foodapp.Models.HandlerCart;
import com.android.foodapp.Models.Message;
import com.android.foodapp.Models.OrderBill;
import com.android.foodapp.Models.OrderBillDetail;
import com.android.foodapp.R;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    public static CartFragment cartFragment;
    List<Cart> listCart;
    CartAdapter adapter;
    RecyclerView recyclerView;
    ScrollView scrollViewCart;
    TextView txtEmpty, txtPriceTotal, txtTotal;
    ConstraintLayout btnPayment, btnDeleteAllCart;
    HandlerCart handlerCart;
    Helper helper;

    public CartFragment(){
        cartFragment = this;
        handlerCart = new HandlerCart();
        listCart =  handlerCart.getCart();
        adapter = new CartAdapter(listCart);
        helper = new Helper();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcvCart);
        scrollViewCart = view.findViewById(R.id.scrollViewCart);
        txtEmpty = view.findViewById(R.id.txtEmpty);
        btnPayment = view.findViewById(R.id.btnPayment_fragmentCart);
        txtPriceTotal = view.findViewById(R.id.txtPriceTotal);
        btnDeleteAllCart = view.findViewById(R.id.btnDelete_fragmentCart);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        HandlerCart handlerCart = new HandlerCart();
        Helper helper = new Helper();
        txtPriceTotal.setText(helper.FormatPrice(handlerCart.getTotal()));


        if(handlerCart.getCart().size() == 0){
            scrollViewCart.setVisibility(View.GONE);
            txtEmpty.setVisibility(View.VISIBLE);
        }
        else{
            txtEmpty.setVisibility(View.GONE);
            scrollViewCart.setVisibility(View.VISIBLE);
        }



        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderBill orderBill = new OrderBill();
                List<OrderBillDetail> listOrderBillDetails = new ArrayList<OrderBillDetail>();
                Account account = Paper.book().read("info");
                List<Cart> listCarts = handlerCart.getCart();
                orderBill.setOrderDate(Date.from(Instant.now()));
                orderBill.setTotal(handlerCart.getTotal());
                orderBill.setPaid(false);
                orderBill.setNote("NOTE");
                orderBill.setIdStatusOrder(1);
                orderBill.setIdCustomer(account.getIdAccount());

                for(int i=0;i<listCarts.size();i++){
                    OrderBillDetail orderBillDetail = new OrderBillDetail();
                    orderBillDetail.setQuantity(listCarts.get(i).getQuantity());
                    orderBillDetail.setSubTotal(listCarts.get(i).getSubTotal());
                    orderBillDetail.setIdFood(listCarts.get(i).getIdFood());
                    listOrderBillDetails.add(orderBillDetail);
                }

                Bill bill = new Bill(orderBill, listOrderBillDetails);
                Order(bill);

                handlerCart.DeleteAllCart();
            }
        });

        btnDeleteAllCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerCart.DeleteAllCart();
                refreshCart(handlerCart.getCart());
            }
        });
    }

    public void refreshCart(List<Cart> cartList) {
        this.listCart.clear();
        this.listCart.addAll(cartList);
        this.adapter.notifyDataSetChanged();

        TextView total =  (TextView)getView().findViewById(R.id.txtPriceTotal);
        TextView txtEmpty =  (TextView)getView().findViewById(R.id.txtEmpty);
        ScrollView scrollViewCart = (ScrollView)getView().findViewById(R.id.scrollViewCart);

        if(handlerCart.getCart().size() == 0){
            scrollViewCart.setVisibility(View.GONE);
            txtEmpty.setVisibility(View.VISIBLE);
        }
        else{
            txtEmpty.setVisibility(View.GONE);
            scrollViewCart.setVisibility(View.VISIBLE);
        }

        total.setText(helper.FormatPrice(handlerCart.getTotal()));

    }

    private void Order(Bill bill){
        try {
            Call call = RetrofitClient.getInstance().getMyApi().Order(bill);
            call.enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    Message message = response.body();
                    String notification = message.getNotification();

                    if(message.getStatus() == 0){
                        Toast.makeText(getActivity(), notification, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getActivity(), message.getNotification().toString(), Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getActivity(), HomeActivity.class));
                            }
                        }, 2000);
                    }
                }
                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }


}