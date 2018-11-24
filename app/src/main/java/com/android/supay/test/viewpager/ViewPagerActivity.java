package com.android.supay.test.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.supay.test.R;
import com.android.supay.test.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.ViewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view_pager);
        ButterKnife.bind(this);
        Util.showLog("ViewpagerActivity","Oncreate ");
        viewPager.setAdapter(new CustomPagerAdapter(this));
    }
}
