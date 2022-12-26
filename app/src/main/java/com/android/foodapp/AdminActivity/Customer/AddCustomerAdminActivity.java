package com.android.foodapp.AdminActivity.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.foodapp.Activity.LoginActivity;
import com.android.foodapp.Activity.RegisterActivity;
import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Message;
import com.android.foodapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomerAdminActivity extends AppCompatActivity {


    EditText edtFullName,edtAddress,edtPhone,edtEmail,edtUsername,edtPassword;
    AppCompatButton btnAddCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_customer);
        edtFullName = findViewById(R.id.txtFullName_AddAdmin);
        edtAddress = findViewById(R.id.txtAddress_AddAdmin);
        edtEmail = findViewById(R.id.txtEmail_AddAdmin);
        edtPhone = findViewById(R.id.txtPhone_AddAdmin);
        edtUsername = findViewById(R.id.txtUsername_AddAdmin);
        edtPassword = findViewById(R.id.txtPassword_AddAdmin);
        btnAddCustomer = findViewById(R.id.btnAddCustomer_AddAdmin);
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = new Account(0, edtFullName.getText().toString(), edtUsername.getText().toString(), edtPassword.getText().toString(), edtPhone.getText().toString(), edtEmail.getText().toString(), "123", 3);
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
                        Toast.makeText(AddCustomerAdminActivity.this, notification, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AddCustomerAdminActivity.this, message.getNotification().toString(), Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(AddCustomerAdminActivity.this, CustomerAdminActivity.class));
                                finish();
                            }
                        }, 2000);
                    }
                }
                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Toast.makeText(AddCustomerAdminActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(AddCustomerAdminActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }
}