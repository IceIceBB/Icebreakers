package com.example.lmont.iceicebb.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by GabeKeyner on 9/25/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FeelingLuckyFragment tab1 = new FeelingLuckyFragment();

                return tab1;
            case 1:
                GamesFragment tab2 = new GamesFragment();

                return tab2;
            case 2:
                MaterialFragment tab3 = new MaterialFragment();

                return tab3;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
