package com.example.catdogtranslator.ui.main;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.catdogtranslator.ui.a.AFragment;
import com.example.catdogtranslator.ui.b.BFragment;

public class MainAdapter extends FragmentStateAdapter {

    public MainAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new BFragment();
        }
        return new AFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
