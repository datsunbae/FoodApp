package com.android.foodapp.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.android.foodapp.AdminActivity.Category.CategoryAdminActivity;
import com.android.foodapp.AdminActivity.Customer.CustomerAdminActivity;
import com.android.foodapp.AdminActivity.Product.ProductAdminActivity;
import com.android.foodapp.AdminActivity.Staff.StaffAdminActivity;
import com.android.foodapp.R;

public class DashboardActivity extends AppCompatActivity {

    CardView btnProduct, btnBill, btnStaff, btnCustomer, btnCategory, btnRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btnProduct = findViewById(R.id.btnProduct_Dashboard);
        btnProduct.setOnClickListener(view -> startActivity(new Intent(DashboardActivity.this, ProductAdminActivity.class)));

        btnBill = findViewById(R.id.btnBill_Dashboard);
        btnBill.setOnClickListener(view -> startActivity(new Intent(DashboardActivity.this, BillAdminActivity.class)));

        btnStaff = findViewById(R.id.btnStaff_Dashboard);
        btnStaff.setOnClickListener(view -> startActivity(new Intent(DashboardActivity.this, StaffAdminActivity.class)));

        btnCustomer = findViewById(R.id.btnCustomer_Dashboard);
        btnCustomer.setOnClickListener(view -> startActivity(new Intent(DashboardActivity.this, CustomerAdminActivity.class)));

        btnCategory = findViewById(R.id.btnCategory_Dashboard);
        btnCategory.setOnClickListener(view -> startActivity(new Intent(DashboardActivity.this, CategoryAdminActivity.class)));
    }
}