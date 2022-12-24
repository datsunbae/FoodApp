package com.android.foodapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.foodapp.Fragment.BillFragment;
import com.android.foodapp.Fragment.CartFragment;
import com.android.foodapp.Fragment.HomeFragment;
import com.android.foodapp.Fragment.ProfileFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 3)
            return new BillFragment();
        else if (position == 2)
            return new CartFragment();
        else if (position == 1)
            return new ProfileFragment();
        else
            return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
