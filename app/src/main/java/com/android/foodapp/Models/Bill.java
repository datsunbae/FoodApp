package com.android.foodapp.Models;

import java.util.List;

public class Bill {
    private OrderBill OrderBills;
    private List<OrderBillDetail> OrderBillDetails;

    public OrderBill getOrderBills() {
        return OrderBills;
    }

    public void setOrderBills(OrderBill orderBills) {
        OrderBills = orderBills;
    }

    public List<OrderBillDetail> getOrderBillDetails() {
        return OrderBillDetails;
    }

    public void setOrderBillDetails(List<OrderBillDetail> orderBillDetails) {
        OrderBillDetails = orderBillDetails;
    }

    public Bill(){}

    public Bill(OrderBill orderBills, List<OrderBillDetail> orderBillDetails) {
        OrderBills = orderBills;
        OrderBillDetails = orderBillDetails;
    }
}
