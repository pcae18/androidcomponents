package com.android.supay.test.practicaViewPager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.supay.test.R;
import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentTareas extends Fragment {

    public static String TAG = FragmentTareas.class.getSimpleName();

    @BindView(R.id.profileImage)
    CircleImageView profileImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tareas, container, false);
        ButterKnife.bind(this, view );
        Util.setUpImageView(Definitions.SRC_IMG_ADVENGERS_IRON_MAN, getActivity(), profileImage);
        return view;
    }

    public static FragmentTareas newInstance(){
        FragmentTareas myInstance = new FragmentTareas();
        return myInstance;
    }
}
