package com.tinhvv.shakeoa.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ray-jason on 22/02/2016.
 */
public class ShakePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public ShakePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ShakePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
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
