package com.android.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodapp.Helper.Helper;
import com.android.foodapp.Models.OrderBill;
import com.android.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderBillAdapter extends RecyclerView.Adapter<OrderBillAdapter.ViewHolder>{

    private List<OrderBill> listOrderBill;
    Helper helper;
    public OrderBillAdapter(List<OrderBill> listOrderBills){
        listOrderBill = listOrderBills;
        helper = new Helper();
    }

    @NonNull
    @Override
    public OrderBillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View foodView = inflater.inflate(R.layout.item_user_order, parent, false);
        OrderBillAdapter.ViewHolder viewHolder = new OrderBillAdapter.ViewHolder(foodView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderBillAdapter.ViewHolder holder, int position) {
        OrderBill orderBill = listOrderBill.get(position);
        holder.txtIdOrder.setText(String.valueOf(orderBill.getIdOrderBill()));
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //holder.txtOrderDate.setText(formatter.format(orderBill.getOrderDate()));
        String status = "";
      //holder.txtTotal.setText(helper.FormatPrice(orderBill.getTotal()));

        switch (orderBill.getIdStatusOrder()){
            case 1:
                status = "Chờ xác nhận";
                break;
            case 2:
                status = "Đã xác nhận";
                break;
            case 3:
                status = "Đang giao hàng";
                break;
            case 4:
                status = "Hoàn thành";
                break;
            case 5:
                status = "Đã hủy";
                break;
        }

        //holder.txtStatusOrder.setText(status);
    }

    @Override
    public int getItemCount() {
        return listOrderBill.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIdOrder, txtOrderDate, txtTotal, txtStatusOrder;
        public ViewHolder(@NonNull View view){
            super(view);
            txtIdOrder = (TextView) view.findViewById(R.id.txtIdOrderBill_itemOrder);
            txtOrderDate = (TextView) view.findViewById(R.id.txtOrderDate__itemOrder);
            txtTotal = (TextView) view.findViewById(R.id.txtTotalOrder_itemOrder);
            txtStatusOrder = (TextView) view.findViewById(R.id.txtStatusOrder__itemOrder);
        }
    }
}
