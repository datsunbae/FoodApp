package com.android.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodapp.Models.Account;
import com.android.foodapp.R;

import java.util.List;

public class Account_Admin_Adapter extends RecyclerView.Adapter<Account_Admin_Adapter.ViewHolder>{
    private List<Account> listAccount;
    public Account_Admin_Adapter(List<Account> listAccounts){
        listAccount = listAccounts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View accountView = inflater.inflate(R.layout.item_admin_staff_customer, parent, false);
        ViewHolder viewHolder = new ViewHolder(accountView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account account = listAccount.get(position);
        holder.tvName_Customer.setText(account.getFullName());
        holder.tvEmail_Customer.setText(account.getEmail());
        holder.tvAddress.setText(account.getAddress());
    }

    @Override
    public int getItemCount() {
        return listAccount.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName_Customer, tvEmail_Customer, tvAddress;
        public ViewHolder(@NonNull View view){
            super(view);
            tvName_Customer = view.findViewById(R.id.nameStaff_Customer_Admin);
            tvEmail_Customer = view.findViewById(R.id.emailStaff_Customer_Admin);
            tvAddress = view.findViewById(R.id.addressStaff_Customer_Admin);
        }
    }
}
