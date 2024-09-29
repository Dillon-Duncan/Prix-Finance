package com.st10079970.prixfinance;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LoginAdapter extends FragmentStateAdapter {

    private int totalTabs;

    public LoginAdapter(@NonNull FragmentActivity fragmentActivity, int totalTabs) {
        super(fragmentActivity);
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LoginTabFragment();
            case 1:
                return new RegisterTabFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}