package com.android.foodapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.foodapp.Activity.MainActivity;
import com.android.foodapp.Activity.ProductUserActivity;
import com.android.foodapp.Activity.SplashActivity;
import com.android.foodapp.Adapter.CategoryAdapter;
import com.android.foodapp.Adapter.FoodAdapter;
import com.android.foodapp.Adapter.FoodRecommendAdapter;
import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Category;
import com.android.foodapp.Models.Food;
import com.android.foodapp.R;
import com.android.foodapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    TextView hello;
    TextView xemThem;
    FoodRecommendAdapter adapterFood;
    RecyclerView recyclerViewFood;
    ConstraintLayout discountConstainLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetFoodRecommend();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewFood = view.findViewById(R.id.rcvRecommended_fragmentHome);

        //welcome
        hello = view.findViewById(R.id.txtName);
        Account account = Paper.book().read("info");
        hello.setText(account.getFullName());

        //Recommend
        xemThem = view.findViewById(R.id.xemThem_fragment_home);
        xemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProductUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetFoodRecommend(){
        try {
            Call call = RetrofitClient.getInstance().getMyApi().GetAllFood();
            call.enqueue(new Callback<List<Food>>() {
                @Override
                public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                    List<Food> listFoods = response.body();
                    int size = listFoods.size();
                    if(size >= 10){
                        size =  10;
                    }
                    adapterFood = new FoodRecommendAdapter(listFoods.subList(0, size));
                    recyclerViewFood.setAdapter(adapterFood);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                    recyclerViewFood.setLayoutManager(gridLayoutManager);
                }
                @Override
                public void onFailure(Call<List<Food>> call, Throwable t) {
                    Toast.makeText(getActivity(),"Lỗi!",Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(getActivity(),"Lỗi!",Toast.LENGTH_SHORT).show();
        }
    }
}