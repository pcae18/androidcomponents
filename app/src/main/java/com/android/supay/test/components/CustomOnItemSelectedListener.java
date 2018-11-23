package com.android.supay.test.components;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 *      Clase encargada del manejo
 * de comportamiento de {@link android.widget.Spinner}
 *
 * @author Paulo_Angeles.
 *
 */
public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e("Select: " , parent.getItemAtPosition(position).toString( ) );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
