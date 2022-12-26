package com.android.foodapp.Activity;
import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.foodapp.Adapter.ViewPager2Adapter;
import com.android.foodapp.Fragment.BillFragment;
import com.android.foodapp.Fragment.CartFragment;
import com.android.foodapp.Fragment.HomeFragment;
import com.android.foodapp.Fragment.ProfileFragment;
import com.android.foodapp.Helper.Helper;
import com.android.foodapp.Models.Cart;
import com.android.foodapp.Models.HandlerCart;
import com.android.foodapp.R;

import java.util.List;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ViewPager2 viewPager2_Home = findViewById(R.id.viewPager2_Home);
        ViewPager2Adapter adapter_home = new ViewPager2Adapter(this);
        viewPager2_Home.setAdapter(adapter_home);

        viewPager2_Home.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3)
                    Toast.makeText(HomeActivity.this, "Don hang", Toast.LENGTH_SHORT).show();
                else if (position == 2)
                {
                    Toast.makeText(HomeActivity.this, "Gio Hang", Toast.LENGTH_SHORT).show();
                }
                else if (position == 1)
                    Toast.makeText(HomeActivity.this, "Thong tin", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

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