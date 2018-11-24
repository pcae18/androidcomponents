package com.android.supay.test.goodviewpager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.supay.test.R;
import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.Util;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentOne extends Fragment {


    public static String TAG = FragmentOne.class.getSimpleName();

    @BindView( R.id.imageViewFragOne )
    ImageView imageViewFragOne;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view );
        Glide.with( getActivity() ).load( Definitions.SRC_IMG_ADVENGERS_IRON_MAN ).into(imageViewFragOne);
        return view;
    }

    public static FragmentOne newInstance(){
        FragmentOne myFragment = new FragmentOne();
        return myFragment;
    }

}
