package com.android.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.foodapp.AdminActivity.DashboardActivity;
import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Food;
import com.android.foodapp.R;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Paper.book().read("info") != null){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
        else{
            edtUsername = (EditText) findViewById(R.id.txtUsername_Login);
            edtPassword = (EditText) findViewById(R.id.txtPassword_Login);

            Button btnLogin_Login = findViewById(R.id.btnLogin_Login);
            btnLogin_Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = edtUsername.getText().toString().trim();
                    String password = edtPassword.getText().toString().trim();
                    Login(username, password);
                }
            });

            Button btnRegister_Login = findViewById(R.id.btnRegister_Login);
            btnRegister_Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    finish();
                }
            });
        }
    }

    private void Login(String username, String password){
        try {
            Call call = RetrofitClient.getInstance().getMyApi().Login(username, password);
            call.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    Account account = response.body();

                    if(account == null){
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Paper.book().write("info", account);
                        if(account.getIdRole() == 1 || account.getIdRole() == 2){
                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                            finish();
                        }
                        else{
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        }
                    }
                }
                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(LoginActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }


}