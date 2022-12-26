package com.android.foodapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.foodapp.Activity.EditProfileActivity;
import com.android.foodapp.Activity.LoginActivity;
import com.android.foodapp.Activity.ProductUserActivity;
import com.android.foodapp.Adapter.CartAdapter;
import com.android.foodapp.Adapter.FoodAdapter;
import com.android.foodapp.Adapter.OrderBillAdapter;
import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Helper.Helper;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Cart;
import com.android.foodapp.Models.Food;
import com.android.foodapp.Models.HandlerCart;
import com.android.foodapp.Models.OrderBill;
import com.android.foodapp.R;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillFragment extends Fragment {

//    List<OrderBill> listOrderBill;
//    OrderBillAdapter adapter;
//    RecyclerView recyclerView;
//    BillFragment billFragment;

    public BillFragment(){
        //billFragment = this;
        //adapter = new OrderBillAdapter(listOrderBill);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Account account = Paper.book().read("info");
        OrderBills(1);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_bill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recyclerView = view.findViewById(R.id.recyclerView_Bill);

//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(itemDecoration);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);


    }

    private void OrderBills(int id){
        try {
            Call call = RetrofitClient.getInstance().getMyApi().Hehe(id);
            call.enqueue(new Callback<List<OrderBill>>() {

                @Override
                public void onResponse(Call<List<OrderBill>> call, Response<List<OrderBill>> response) {
                    Toast.makeText(getActivity(), "Aloo", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<List<OrderBill>> call, Throwable t) {

                }
            });
        }
        catch (Exception ex){
            Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }
}