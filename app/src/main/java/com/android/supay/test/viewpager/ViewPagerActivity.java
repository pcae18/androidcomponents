package com.android.supay.test.viewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.supay.test.R;

/**
 *      {@link android.app.Activity} que muestra el comportamiento
 * de un {@link android.support.v4.view.ViewPager }.
 *
 */
public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
    }
}
