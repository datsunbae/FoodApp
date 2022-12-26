package com.android.foodapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.foodapp.Activity.EditProfileActivity;
import com.android.foodapp.Activity.LoginActivity;
import com.android.foodapp.Activity.MainActivity;
import com.android.foodapp.Activity.ProductUserActivity;
import com.android.foodapp.Activity.SplashActivity;
import com.android.foodapp.Adapter.CategoryAdapter;
import com.android.foodapp.Models.Account;
import com.android.foodapp.Models.Category;
import com.android.foodapp.R;

import java.util.ArrayList;

import io.paperdb.Paper;

public class ProfileFragment extends Fragment {

    TextView nameUser, addressUser, phoneUser, emailUser;
    ConstraintLayout btnUpdateProfile, btnLogout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameUser = view.findViewById(R.id.txtName_FragmentProfile);
        addressUser = view.findViewById(R.id.txtAddress_FragmentProfile);
        phoneUser = view.findViewById(R.id.txtPhone_FragmentProfile);
        emailUser = view.findViewById(R.id.txtEmail_FragmentProfile);
        btnUpdateProfile = view.findViewById(R.id.btnEditProfile_FragmentProfile);
        btnLogout = view.findViewById(R.id.logOut);


        Account account = Paper.book().read("info");
        nameUser.setText(account.getFullName());
        addressUser.setText(account.getAddress());
        phoneUser.setText(account.getPhone());
        emailUser.setText(account.getEmail());

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().delete("info");
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }
}