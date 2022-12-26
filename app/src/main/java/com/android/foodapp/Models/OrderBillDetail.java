package com.android.foodapp.Models;

public class OrderBillDetail {
    private int IdOrderBillDetails;
    private int Quantity;
    private double SubTotal;
    private int IdOrderBill;
    private int IdFood;

    public OrderBillDetail(){
    }

    public OrderBillDetail(int idOrderBillDetails, int quantity, double subTotal, int idOrderBill, int idFood) {
        IdOrderBillDetails = idOrderBillDetails;
        Quantity = quantity;
        SubTotal = subTotal;
        IdOrderBill = idOrderBill;
        IdFood = idFood;
    }

    public int getIdOrderBillDetails() {
        return IdOrderBillDetails;
    }

    public void setIdOrderBillDetails(int idOrderBillDetails) {
        IdOrderBillDetails = idOrderBillDetails;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double subTotal) {
        SubTotal = subTotal;
    }

    public int getIdOrderBill() {
        return IdOrderBill;
    }

    public void setIdOrderBill(int idOrderBill) {
        IdOrderBill = idOrderBill;
    }

    public int getIdFood() {
        return IdFood;
    }

    public void setIdFood(int idFood) {
        IdFood = idFood;
    }


}
