package com.android.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Message;
import com.android.foodapp.R;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText edtFullName;
    EditText edtAddress;
    EditText edtPhone;
    EditText edtEmail;
    EditText edtUsername;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnLogin_Register = findViewById(R.id.btnRegister_Register);
        btnLogin_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtFullName = findViewById(R.id.txtFullName_Register);
                edtAddress = findViewById(R.id.txtAddress_Register);
                edtEmail = findViewById(R.id.txtEmail_Register);
                edtPhone = findViewById(R.id.txtPhone_Register);
                edtUsername = findViewById(R.id.txtUsername_Register);
                edtPassword = findViewById(R.id.txtPassword_Register);

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
                        Toast.makeText(RegisterActivity.this, notification, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, message.getNotification().toString(), Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }
                        }, 2000);
                    }
                }
                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(RegisterActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }

}