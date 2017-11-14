package com.example.dk.mytest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * ViewPager + Fragment 通用的 Adapter
 *
 * Created by dk on 2017/8/25.
 */

public class CommonPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public CommonPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        Log.e("CommonPagerAdapter==", fragments.toString());
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
