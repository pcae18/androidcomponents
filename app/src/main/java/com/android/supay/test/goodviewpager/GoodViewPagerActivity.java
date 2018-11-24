package com.android.supay.test.goodviewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.supay.test.R;
import com.android.supay.test.goodviewpager.fragments.FragmentOne;
import com.android.supay.test.goodviewpager.fragments.FragmentThree;
import com.android.supay.test.goodviewpager.fragments.FragmentTwo;
import com.android.supay.test.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private String TAG = GoodViewPagerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_view_pager);
        ButterKnife.bind(this);
        setUpViewPager();
    }

    private void setUpViewPager() {
        GoodPagerAdapter goodPagerAdapter = new GoodPagerAdapter(getSupportFragmentManager());
        goodPagerAdapter.addFragment(FragmentOne.newInstance(),FragmentOne.TAG);
        goodPagerAdapter.addFragment(FragmentTwo.newInstance("Prueba de enviar datos a fragments"),
                FragmentTwo.TAG);
        goodPagerAdapter.addFragment(FragmentThree.newInstance(),FragmentThree.TAG);
        viewPager.setAdapter(goodPagerAdapter);
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        Log.e(TAG,"Page selected "+i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        Log.e(TAG,"on page scroll "+i);
    }

}
