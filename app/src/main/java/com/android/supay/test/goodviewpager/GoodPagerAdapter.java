package com.android.supay.test.goodviewpager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.supay.test.util.Util;

import java.util.ArrayList;
import java.util.List;

public class GoodPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public GoodPagerAdapter( FragmentManager manager ) {
        super(manager);
        Util.showLog("Adapter", manager.toString( ) );

    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get( i );
    }

    @Override
    public int getCount() {
        return mFragmentTitleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get( position );
    }

    public void addFragment(Fragment fragment, String title ){
        mFragmentList.add( fragment );
        mFragmentTitleList.add( title );

    }
}
