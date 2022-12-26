package com.android.foodapp.AdminActivity.Category;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.foodapp.R;

public class CategoryAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);
        ConstraintLayout btnAdd_Category = findViewById(R.id.btnAdd_Category);
        btnAdd_Category.setOnClickListener(view -> startActivity(new Intent(CategoryAdminActivity.this, AddCategoryAdminActivity.class)));
    }
}