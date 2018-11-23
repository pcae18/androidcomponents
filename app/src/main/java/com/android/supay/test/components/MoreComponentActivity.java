package com.android.supay.test.components;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.supay.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 *      {@link android.app.Activity} donde se mostrarán
 *      nuevos {@link android.view.View} como Spineers.
 *
 * @author Paulo_Angeles.
 */
public class MoreComponentActivity extends AppCompatActivity {

    String TAG = MoreComponentActivity.class.getSimpleName();

    Spinner mSpinnerCountry;
    Spinner mSpinnerList;
    Button mButtonSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_more_component );
        initViewElements();
        addListenerOnSpinnerItemSelection();
        addItemsOnSpinnerList();
    }

    public void initViewElements(){
        mSpinnerCountry = findViewById( R.id.spinnerCountry );
        mSpinnerList = findViewById( R.id.spinnerList );
        mButtonSelect = findViewById( R.id.buttonSelect );
    }

    public void addListenerOnSpinnerItemSelection(){
        mSpinnerCountry.setOnItemSelectedListener( new CustomOnItemSelectedListener( ) );
    }

    public void addItemsOnSpinnerList(){
        List<String> list = new ArrayList<>();
        list.add("Elemento 1");
        list.add("Elemento 2");
        list.add("Elemento 3");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, list );
        dataAdapter.setDropDownViewResource( R.layout.spinner_item );
        mSpinnerList.setAdapter(dataAdapter);
    }

    public void addListenerButtonSelect( View v ){
        mButtonSelect.setOnClickListener( new View.OnClickListener(){
              @Override
              public void onClick(View v) {
                createSimpleDialog( MoreComponentActivity.this );
              }
          }
        );
    }

    public void createSimpleDialog( Context context ){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Seleccionaste")
                .setMessage("Spinner 1 : " + String.valueOf( mSpinnerCountry.getSelectedItem( ) )
                    + "\n Spinner  2" + String.valueOf( mSpinnerList.getSelectedItem() )
                ).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
