package com.android.supay.test.practicaViewPager.fragments;

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
import com.android.supay.test.util.KeyDefinitions;
import com.android.supay.test.util.Util;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentDireccion extends Fragment {

    public static String TAG = FragmentDireccion.class.getSimpleName();
    private HashMap<String, String> data;

    @BindView(R.id.textViewAddress)
    TextView textViewAddress;

    @BindView(R.id.imageViewMap)
    ImageView imageViewMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_direccion, container, false);
        ButterKnife.bind(this, view);
        if(getArguments() != null){
            data = (HashMap<String, String>)getArguments().getSerializable("data");
            textViewAddress.setText(data.get(KeyDefinitions.ADDRESS));
            Util.setUpImageView(Definitions.SRC_IMG_MAP_ADDRESS, getActivity(), imageViewMap);
        }
        return view;

    }

    public static FragmentDireccion newInstance(HashMap<String,String> data){
        FragmentDireccion myInstance = new FragmentDireccion();
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        myInstance.setArguments(args);
        return myInstance;
    }
}
