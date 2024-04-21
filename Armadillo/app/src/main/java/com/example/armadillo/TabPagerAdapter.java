package com.example.armadillo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_TABS = 5;

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Module1Fragment();
            case 1:
                return new Modul1_1Fragment();
            case 2:
                return new Module2Fragment();
            case 3:
                return new Module3Fragment();
            case 4:
                return new Module4Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }

    public String getTitle(int position) {
        switch (position) {
            case 0:
                return "Модуль 1.1";
            case 1:
                return "Модуль 1.2";
            case 2:
                return "Модуль 2";
            case 3:
                return "Модуль 3";
            case 4:
                return "Модуль 4";
            default:
                return null;
        }
    }
}
