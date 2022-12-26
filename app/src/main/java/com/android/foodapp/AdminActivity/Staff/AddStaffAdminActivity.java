package com.android.foodapp.AdminActivity.Staff;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.foodapp.AdminActivity.Customer.AddCustomerAdminActivity;
import com.android.foodapp.AdminActivity.Customer.CustomerAdminActivity;
import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Message;
import com.android.foodapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStaffAdminActivity extends AppCompatActivity {

    EditText edtFullName,edtAddress,edtPhone,edtEmail,edtUsername,edtPassword;
    AppCompatButton btnAddStaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_staff);
        edtFullName = findViewById(R.id.txtFullName_AdminStaff);
        edtAddress = findViewById(R.id.txtAddress_AdminStaff);
        edtEmail = findViewById(R.id.txtEmail_AdminStaff);
        edtPhone = findViewById(R.id.txtPhone_AdminStaff);
        edtUsername = findViewById(R.id.txtUsername_AdminStaff);
        edtPassword = findViewById(R.id.txtPassword_AdminStaff);
        btnAddStaff = findViewById(R.id.btnAddStaff_AdminStaff);
        btnAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = new Account(0, edtFullName.getText().toString(), edtUsername.getText().toString(), edtPassword.getText().toString(), edtPhone.getText().toString(), edtEmail.getText().toString(), "123", 2);
                Register(account);
            }
        });
    }
    private void Register(Account account){
        try {
            Call call = RetrofitClient.getInstance().getMyApi().Register(account);
            call.enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    Message message = response.body();
                    String notification = message.getNotification();

                    if(message.getStatus() == 0){
                        Toast.makeText(AddStaffAdminActivity.this, notification, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AddStaffAdminActivity.this, message.getNotification().toString(), Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(AddStaffAdminActivity.this, StaffAdminActivity.class));
                                finish();
                            }
                        }, 2000);
                    }
                }
                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Toast.makeText(AddStaffAdminActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(AddStaffAdminActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }
}