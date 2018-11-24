package com.android.supay.test.goodviewpager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.supay.test.R;
import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentTwo extends Fragment {

    public static String TAG = FragmentOne.class.getSimpleName();
    String message;
    @BindView(R.id.textViewFragTwo)
    TextView textView;

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            message = getArguments().getString(Definitions.FRAGMENT_ARG_FLAG);
            Util.showLog(TAG, "Message received: " + message );
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_two, container, false);
        ButterKnife.bind(this, view );
        textView.setText( message );
        return view;
    }

    public static FragmentTwo newInstance( String someString ){
        FragmentTwo myFragment = new FragmentTwo();
        Bundle args = new Bundle();
        args.putString(Definitions.FRAGMENT_ARG_FLAG, someString);
        myFragment.setArguments(args);
        return myFragment;
    }

}
