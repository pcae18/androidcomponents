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

public class FragmentDatosUsuario extends Fragment {

    @BindView(R.id.textViewName)
    TextView textViewName;

    @BindView(R.id.textViewLastName)
    TextView textViewLastName;

    @BindView(R.id.textViewEmail)
    TextView textViewEmail;


    public static String TAG = FragmentDatosUsuario.class.getSimpleName();
    private HashMap<String, String> data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos_usuario, container, false);
        ButterKnife.bind(this, view);
        if(getArguments()!=null){
            data = (HashMap<String,String>)getArguments().getSerializable("data");
            textViewName.setText(data.get(KeyDefinitions.NAME));
            textViewLastName.setText(data.get(KeyDefinitions.LAST_NAME));
            textViewEmail.setText(data.get(KeyDefinitions.EMAIL));

        }
        return view;
    }

    public static FragmentDatosUsuario newInstance(HashMap<String,String> data){
        FragmentDatosUsuario myInstance = new FragmentDatosUsuario();
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        myInstance.setArguments(args);
        return myInstance;
    }
}
