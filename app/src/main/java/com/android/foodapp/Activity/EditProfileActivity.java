package com.android.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.foodapp.Api.RetrofitClient;
import com.android.foodapp.Fragment.ProfileFragment;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Food;
import com.android.foodapp.Models.Message;
import com.android.foodapp.R;
import com.bumptech.glide.Glide;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    EditText txtName, txtAddress, txtPhone, txtEmail, txtPassword;
    AppCompatButton btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_profile);

        txtName = findViewById(R.id.txtFullName_Profile);
        txtAddress = findViewById(R.id.txtAddress_Profile);
        txtPhone = findViewById(R.id.txtPhone_Profile);
        txtEmail = findViewById(R.id.txtEmail_Profile);
        txtPassword = findViewById(R.id.txtPassword_Profile);
        btnEdit = findViewById(R.id.btnProfile_Edit_User);

        Account account = Paper.book().read("info");
        txtName.setText(account.getFullName());
        txtAddress.setText(account.getAddress());
        txtPhone.setText(account.getPhone());
        txtEmail.setText(account.getEmail());
        txtPassword.setText(account.getPasswordAccount());
        btnEdit.setOnClickListener(view -> {
            Account editAccount = new Account(account.getIdAccount(),txtName.getText().toString(), account.getUserName(), txtPassword.getText().toString(), txtPhone.getText().toString(), txtEmail.getText().toString(), txtAddress.getText().toString(), account.getIdRole());
            EditAccount(editAccount);
        });
    }

    private void EditAccount(Account account){
        try {
            Call call = RetrofitClient.getInstance().getMyApi().EditAccount(account);
            call.enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    Message message = response.body();
                    String notification = message.getNotification();

                    if(message.getStatus() == 0){
                        Toast.makeText(EditProfileActivity.this, notification, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(EditProfileActivity.this, message.getNotification().toString(), Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(() -> {
                            startActivity(new Intent(EditProfileActivity.this, HomeActivity.class));
                            finish();
                        }, 2000);
                    }
                }
                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Toast.makeText(EditProfileActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(EditProfileActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }

}