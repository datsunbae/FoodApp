package com.android.foodapp.AdminActivity.Customer;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodapp.Adapter.Account_Admin_Adapter;
import com.android.foodapp.R;

public class CustomerAdminActivity extends AppCompatActivity {

    public Account_Admin_Adapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer);
        ConstraintLayout btnAdd_Customer = findViewById(R.id.btnAdd_Customer);
        btnAdd_Customer.setOnClickListener(view -> startActivity(new Intent(CustomerAdminActivity.this, AddCustomerAdminActivity.class)));
        recyclerView = findViewById(R.id.rcvCustomer_Admin);

    }
}