package com.example.jatinschattingapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.jatinschattingapp.fragment.Callsfragment;
import com.example.jatinschattingapp.fragment.ChatFragment;
import com.example.jatinschattingapp.fragment.Statusfragment;

import org.jetbrains.annotations.NotNull;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return  new ChatFragment();
            case 1: return  new Statusfragment();
            case 2: return  new Callsfragment();
            default: return new ChatFragment();



        }
    }

    @Override
    public int getCount() {

        return 3;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String tittle =null;
        if (position==0){
            tittle="CHATS";
        }
        if (position==1){
            tittle="STATUS";
        }
        if (position==2){
            tittle= "CALLS";
        }
        return tittle;
    }
}
