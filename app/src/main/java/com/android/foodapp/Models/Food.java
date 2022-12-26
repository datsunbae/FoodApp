package com.android.foodapp.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Food implements Serializable {
    @SerializedName("IdFood")
    private int IdFood;
    @SerializedName("NameFood")
    private String NameFood;
    @SerializedName("Price")
    private double Price;
    @SerializedName("PercentPromotion")
    private int PercentPromotion;
    @SerializedName("Description")
    private String Description;
    @SerializedName("Img")
    private String Img;
    @SerializedName("Status")
    private boolean Status;
    @SerializedName("TypeFood")
    private boolean TypeFood;
    @SerializedName("IsPublished")
    private boolean IsPublished;
    @SerializedName("IdCategory")
    private int IdCategory;

    public Food(){}

    public Food(int idFood, String nameFood, double price, int percentPromotion, String description, String img, boolean status, boolean typeFood, boolean isPublished, int idCategory) {
        IdFood = idFood;
        NameFood = nameFood;
        Price = price;
        PercentPromotion = percentPromotion;
        Description = description;
        Img = img;
        Status = status;
        TypeFood = typeFood;
        IsPublished = isPublished;
        IdCategory = idCategory;
    }

    public int getIdFood() {
        return IdFood;
    }

    public void setIdFood(int idFood) {
        IdFood = idFood;
    }

    public String getNameFood() {
        return NameFood;
    }

    public void setNameFood(String nameFood) {
        NameFood = nameFood;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getPercentPromotion() {
        return PercentPromotion;
    }

    public void setPercentPromotion(int percentPromotion) {
        PercentPromotion = percentPromotion;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean isTypeFood() {
        return TypeFood;
    }

    public void setTypeFood(boolean typeFood) {
        TypeFood = typeFood;
    }

    public boolean isPublished() {
        return IsPublished;
    }

    public void setPublished(boolean published) {
        IsPublished = published;
    }

    public int getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(int idCategory) {
        IdCategory = idCategory;
    }
}
