package com.android.foodapp.Models;

import java.util.List;

public class ListFood {
    private List<Food> foodList;

    public ListFood(){}

    public ListFood(List<Food> foodList) {
        this.foodList = foodList;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }



}
