package com.android.supay.test.goodviewpager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.supay.test.R;
import com.android.supay.test.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentThree extends Fragment {

    public static String TAG = FragmentOne.class.getSimpleName();

    @BindView( R.id.buttonFragThree )
    Button buttonFragThree;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_three, container, false);
        ButterKnife.bind(view,getActivity());
        ButterKnife.bind(this,view);
        return view;
    }

    public static FragmentThree newInstance(){
        FragmentThree myFragment = new FragmentThree();
        return myFragment;
    }

    @OnClick( R.id.buttonFragThree )  public void onClickButton( View view ){
        Util.showLog("ASDASD", "AASDASDASDASDASDSADASDSAD");
        Util.showToast("Has iniciado sesión", getActivity() );
    }



}
