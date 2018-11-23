package com.android.supay.test.components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import com.android.supay.test.R;
import com.bumptech.glide.Glide;

import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.Util;

/**
 * Actividad para mostrar
 * components de vista.
 *
 */
public class ComponentsActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = ComponentsActivity.class.getSimpleName();

    private LinearLayout mLinearLayout;
    private Button mBtnThird;

    private ImageView imageViewLoad;
    private EditText editTextNombre;
    private CheckBox checkBoxTerminosCondiciones;
    private RadioGroup radioGroupAcepto;
    private Switch switchRecibirNotificaciones;
    private ProgressBar progressBar;
    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components);
        initViewElements();

        mBtnThird.setOnClickListener(this);
        mLinearLayout.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //==================================
                Util.showLog(TAG, "Click set OnClickListener");
                //==================================
            }
        });
        setUpImageView();
        setUpCheckBox();
        setUpRadioGroup();
        setUpSwitch();
        progressBar.setVisibility(View.GONE);
    }

    /**
     *      Método encargado de inicializar
     * los elementos de la vista.
     *
     * @author Paulo_Angeles.
     */
    public void initViewElements(){

        mBtnThird = findViewById( R.id.buttonOverride );
        mLinearLayout = findViewById( R.id.linearLayout );

        imageViewLoad = findViewById( R.id.imageViewLoad );
        editTextNombre = findViewById( R.id.editTextName );
        checkBoxTerminosCondiciones = findViewById( R.id.checkBoxTerminosCondiciones );
        radioGroupAcepto = findViewById( R.id.radioGroupAcepto );
        switchRecibirNotificaciones = findViewById( R.id.switchRecibirNotificacion );
        progressBar = findViewById( R.id.progressBar );
        scrollView = findViewById( R.id.scrollView);
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
    public void setUpImageView(){

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable( this);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent ),
                PorterDuff.Mode.SRC_ATOP );
        circularProgressDrawable.start();

        Glide.with( this)
                .load( Definitions.SUPAY_GIF )
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .crossFade(5000)
                .into(imageViewLoad);
    }

    /**
     *
     *  Método encargado de escuchar
     * el elemento CHECKBOX.
     *
     * @author Paulo_Angeles.
     *
     */
    public void setUpCheckBox(){
        checkBoxTerminosCondiciones.setOnCheckedChangeListener( onCheckedChangeListener );
    }

    public void setUpRadioGroup(){
        radioGroupAcepto.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch( checkedId ){
                    case R.id.radioButtonAceptoSi:
                        showToast("Seleccionaste Sí!" , getApplicationContext() );
                        break;
                    case R.id.radioButtonAceptoNo:
                        showToast("Seleccionaste No!" , getApplicationContext() );
                        break;
                }
            }
        });
    }

    /**
     *
     * @author Paulo_Angeles.
     */
    public void setUpSwitch(){
        switchRecibirNotificaciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showHideProgressBar( progressBar );
                /**if( isChecked ){
                }else{

                }**/
            }
        });
    }

    /**
     *      Método encargado de mostrar u
     *  ocultar un {@link ProgressBar}.
     *
     * @author  Paulo_Angeles.
     * @param progressBarView   {@link ProgressBar} a  mostrar/ocultar.
     */
    private void showHideProgressBar( View progressBarView ){
        if( progressBarView.getVisibility() == View.VISIBLE ){
            progressBarView.setVisibility( View.GONE );
        }else{
            progressBarView.setVisibility( View.VISIBLE );

        }
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Util.showLog(TAG, "Is checked : " + isChecked);
            showSnackBar( "Is checked : " + isChecked, scrollView );
        }
    };

    /**
     *      Método encargado de mostrar
     *  un {@link Snackbar} con un mensaje
     *  definido.
     *
     * @author  Paulo_Angeles.
     *
     * @param parentLayout      {@link View} parentLayout donde se mostrará el {@link Snackbar}
     * @param message           Mensaje a mostrar.
     */
    private void showSnackBar(String message, View parentLayout){
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG )
                .show();
    }

    /**
     *      Método encargado de mostrar
     *  un {@link android.widget.Toast} con un mensaje
     *  definido.
     *
     * @author  Paulo_Angeles.
     *
     * @param context           {@link Context} contexto donde se mostrará el {@link android.widget.Toast}
     * @param message           Mensaje a mostrar.
     */
    private void showToast(String message, Context context){
        Toast.makeText( context, message, Snackbar.LENGTH_LONG )
                .show();
    }


    /**
     *
     *
     *
     * @param   view
     */
    public void clicked(View view ){
        //==================================
        Util.showLog(TAG, "Click function XML.");
        //===================================

    }

    /**
     *
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch( v.getId( ) ){
            case R.id.buttonOverride:
                //==================================
                Util.showLog(TAG, "Click method onClick override.");
                //===================================
                if( !editTextNombre.getText( ).toString( ).isEmpty( ) ){
                    showSnackBar( editTextNombre.getText( ).toString( ), scrollView );
                }
                break;
        }

    }
}
