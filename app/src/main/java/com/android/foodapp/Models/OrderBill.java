package com.android.foodapp.Models;

import java.util.Date;

public class OrderBill {
    private int IdOrderBill;
    private Date OrderDate;
    private Date ShipDate;
    private double Total;
    private boolean IsPaid;
    private String Note;
    private int IdStatusOrder;
    private int IdCustomer;

    public OrderBill(){}

    public OrderBill(int idOrderBill, Date orderDate, Date shipDate, double total, boolean isPaid, String note, int idStatusOrder, int idCustomer) {
        IdOrderBill = idOrderBill;
        OrderDate = orderDate;
        ShipDate = shipDate;
        Total = total;
        IsPaid = isPaid;
        Note = note;
        IdStatusOrder = idStatusOrder;
        IdCustomer = idCustomer;
    }

    public int getIdOrderBill() {
        return IdOrderBill;
    }

    public void setIdOrderBill(int idOrderBill) {
        IdOrderBill = idOrderBill;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public Date getShipDate() {
        return ShipDate;
    }

    public void setShipDate(Date shipDate) {
        ShipDate = shipDate;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public boolean isPaid() {
        return IsPaid;
    }

    public void setPaid(boolean paid) {
        IsPaid = paid;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public int getIdStatusOrder() {
        return IdStatusOrder;
    }

    public void setIdStatusOrder(int idStatusOrder) {
        IdStatusOrder = idStatusOrder;
    }

    public int getIdCustomer() {
        return IdCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        IdCustomer = idCustomer;
    }
}
