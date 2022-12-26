package com.android.foodapp.Models;

public class StatusOrder {
    private int IdStatusOrder;
    private String NameStatus;

    public StatusOrder(){}

    public StatusOrder(int idStatusOrder, String nameStatus) {
        IdStatusOrder = idStatusOrder;
        NameStatus = nameStatus;
    }

    public int getIdStatusOrder() {
        return IdStatusOrder;
    }

    public void setIdStatusOrder(int idStatusOrder) {
        IdStatusOrder = idStatusOrder;
    }

    public String getNameStatus() {
        return NameStatus;
    }

    public void setNameStatus(String nameStatus) {
        NameStatus = nameStatus;
    }



}
