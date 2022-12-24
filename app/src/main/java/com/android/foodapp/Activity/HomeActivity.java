package com.android.foodapp.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.foodapp.Adapter.ViewPager2Adapter;
import com.android.foodapp.R;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager2 viewPager2_Home = findViewById(R.id.viewPager2_Home);
        ViewPager2Adapter adapter_home = new ViewPager2Adapter(this);
        viewPager2_Home.setAdapter(adapter_home);

        LinearLayout buttonHome = findViewById(R.id.btnHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2_Home.setCurrentItem(0);
            }
        });
        LinearLayout buttonCart = findViewById(R.id.btnCart);
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2_Home.setCurrentItem(2);
            }
        });
        LinearLayout buttonProfile = findViewById(R.id.btnProfile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2_Home.setCurrentItem(1);
            }
        });
        LinearLayout btnBill = findViewById(R.id.btnBill_Home);
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2_Home.setCurrentItem(3);
            }
        });
    }
}