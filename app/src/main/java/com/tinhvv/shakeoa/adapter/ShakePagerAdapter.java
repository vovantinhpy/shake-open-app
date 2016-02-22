package com.tinhvv.shakeoa.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tinhvv.shakeoa.fragment.DemoFragment;
import com.tinhvv.shakeoa.fragment.ShakeOAFragment;

/**
 * Created by ray-jason on 22/02/2016.
 */
public class ShakePagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public ShakePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ShakePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                DemoFragment tab1 = new DemoFragment();
                return tab1;
            case 1:
                ShakeOAFragment tab2 = new ShakeOAFragment();
                return tab2;
            case 2:
                ShakeOAFragment tab3 = new ShakeOAFragment();
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
