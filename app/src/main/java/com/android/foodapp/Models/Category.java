package com.android.foodapp.Models;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.foodapp.Adapter.CategoryAdapter;
import com.android.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public String title;
    public String image;

    public Category(String titles, String images) {
        title = titles;
        image = images;
    }

    public static List<Category> getCategory() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Pizza","cat_1"));
        categoryList.add(new Category("Burger","cat_2"));
        categoryList.add(new Category("Hotdog","cat_3"));
        categoryList.add(new Category("Drink","cat_4"));
        categoryList.add(new Category("Donut","cat_5"));
        return categoryList;
    }
}
