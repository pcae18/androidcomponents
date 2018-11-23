package com.android.supay.test.avengerscomponent;

import android.graphics.PorterDuff;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.android.supay.test.R;
import com.android.supay.test.components.CustomOnItemSelectedListener;
import com.android.supay.test.model.Avenger;
import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.Util;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AvengerActivity extends AppCompatActivity {

    String TAG = AvengerActivity.class.getSimpleName();

    private Spinner spinnerNombreAvenger;
    private EditText editTextFrase;
    private Avenger avenger;
    private Switch switchVivo;
    private RadioGroup radioGroupTieneGema;
    private Button buttonGuardar;

    private ConstraintLayout frmAltaAvenger;
    private ConstraintLayout frmDetalleAvenger;

    private HashMap<String, String> imageAdvengers;

    private ImageView detalleImageViewAdvenger;
    private TextView detalleNombreAvenger;
    private TextView detalleFraseAvenger;
    private CheckBox detalleCheckBoxGema;
    private CheckBox detalleCheckBoxVivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avenger);
        initComponentView();
        addItemsOnSpinnerList();
        frmAltaAvenger.setVisibility( View.VISIBLE );
        frmDetalleAvenger.setVisibility( View.GONE );
    }

    public void initComponentView(){
        avenger = new Avenger();
        spinnerNombreAvenger = findViewById( R.id.spinnerNombreAvenger );
        editTextFrase = findViewById( R.id.editTextFrase );
        switchVivo = findViewById(R.id.switchVivo);
        radioGroupTieneGema = findViewById( R.id.radioGroupGema );
        buttonGuardar = findViewById( R.id.buttonGuardar );
        frmAltaAvenger = findViewById( R.id.altaAdvengerLayout );
        frmDetalleAvenger = findViewById( R.id.detalleAdvengerLayout );

        imageAdvengers = new HashMap<String, String>();
        imageAdvengers.put( Definitions.AVENGERS_MAPACHE, Definitions.SRC_IMG_ADVENGERS_MAPACHE );
        imageAdvengers.put( Definitions.AVENGERS_IRON_MAN, Definitions.SRC_IMG_ADVENGERS_IRON_MAN );
        imageAdvengers.put( Definitions.AVENGERS_SPIDERMAN, Definitions.SRC_IMG_ADVENGERS_SPIDERMAN );

        detalleImageViewAdvenger = findViewById( R.id.imageViewDetalleAvenger );
        detalleNombreAvenger = findViewById( R.id.textViewNombre );
        detalleFraseAvenger = findViewById( R.id.textViewFrase );
        detalleCheckBoxGema = findViewById( R.id.checkBoxGema );
        detalleCheckBoxVivo = findViewById( R.id.checkBoxVivo );

    }

    public void setDetailInfo(){
        Util.showHideViewComponent( frmAltaAvenger );
        Util.showHideViewComponent( frmDetalleAvenger );
        setUpImageView( imageAdvengers.get( spinnerNombreAvenger.getSelectedItem().toString() ));
        detalleNombreAvenger.setText( spinnerNombreAvenger.getSelectedItem().toString( ) );
        detalleFraseAvenger.setText( editTextFrase.getText( ) );
        int index = radioGroupTieneGema.indexOfChild(findViewById(radioGroupTieneGema.getCheckedRadioButtonId()));
        detalleCheckBoxGema.setChecked( index==0? true:false  );
        detalleCheckBoxVivo.setChecked( switchVivo.isChecked( ) );
    }

    public void onClickGuardar( View view){
        if( !editTextFrase.getText().toString().isEmpty() ){
            setDetailInfo();
        }else{
            Util.showToast( Definitions.MESSAGE_ERROR_REQUIRED_FRASE , getApplicationContext( ) );
        }

    }

    public void onClickRegresar( View view){
        Util.showHideViewComponent( frmAltaAvenger );
        Util.showHideViewComponent( frmDetalleAvenger );

    }

    /**
     *
     * Método encargado de cargar
     * imagen a un componente
     *
     * @author Paulo_Angeles.
     *
     * Nota:Alternativa Picasso, pero es más robusta.
     */
    public void setUpImageView( String src ){
        Util.showLog( TAG, "index" + src);

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable( this);
        circularProgressDrawable.setStrokeWidth(20f);
        circularProgressDrawable.setCenterRadius(60f);
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent ),
                PorterDuff.Mode.SRC_ATOP );
        circularProgressDrawable.start();

        Glide.with( this)
                .load( src )
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .crossFade(5000)
                .into(detalleImageViewAdvenger);
    }

    public void addItemsOnSpinnerList(){

        List<String> avengersName = new ArrayList<>();
        avengersName.add(Definitions.AVENGERS_MAPACHE);
        avengersName.add(Definitions.AVENGERS_SPIDERMAN);
        avengersName.add(Definitions.AVENGERS_IRON_MAN);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, avengersName );
        dataAdapter.setDropDownViewResource( R.layout.support_simple_spinner_dropdown_item );
        spinnerNombreAvenger.setAdapter(dataAdapter);
    }

}
