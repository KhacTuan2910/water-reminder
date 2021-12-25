package com.example.waterreminder;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.waterreminder.fragment.HistoryFragment;
import com.example.waterreminder.fragment.HomeFragment;
import com.example.waterreminder.fragment.MyProfileFragment;

public class MyViewPager2Adapter extends FragmentStateAdapter {
    public MyViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new HistoryFragment();
            case 2:
                return new MyProfileFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
