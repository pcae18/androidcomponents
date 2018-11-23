package com.android.supay.test.cambioImagenComponent;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.supay.test.R;

import com.android.supay.test.util.Util;

public class CambioImagenActivity extends AppCompatActivity{

    String TAG = AppCompatActivity.class.getSimpleName();

    private TextView mSeleccionadoTextView;
    private ImageView mImagenSeleccionadaTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practica1);
        mImagenSeleccionadaTextView = findViewById( R.id.linearLayoutImage );
        mImagenSeleccionadaTextView.setScaleType( ImageView.ScaleType.CENTER_CROP );
        mSeleccionadoTextView = findViewById( R.id.textView );
    }

    /**
     * Método encargado de vincular
     * los botones de la actividad
     * y setear atributos de los
     * personajes seleccionados.
     *
     * @param v   View seleccionada.
     */
    public void clickedCharacter( View v ) {
        Util.showLog(TAG, "On Click.");
        switch( v.getId( ) ){
            case R.id.btnSaxofon:
                Util.showLog(TAG, "Saxofón Button!.");
                setCharacterInfo( "Has seleccionado Saxofón", R.drawable.moe_szyslak_2  ,R.color.colorPrimary);
                break;

            case R.id.btnCerveza:
                Util.showLog(TAG, "Puerco araña Button!.");
                setCharacterInfo( "Has seleccionado Cerveza.", R.drawable.lisa_simpson  ,R.color.colorPrimaryDark);
                break;

            case R.id.linearLayoutBtnPuerco:
                Util.showLog(TAG, "Puerco araña Button!.");
                setCharacterInfo( "Has seleccionado Puerco araña.", R.drawable.bart_simpson  ,R.color.colorPrimary);
                break;

            case R.id.linearLayoutBtnPatineta:
                Util.showLog(TAG, "Patineta Button!.");
                setCharacterInfo( "Has seleccionado Patineta.", R.drawable.homer2  ,R.color.colorRedText);
                break;
        }
    }

    /**
     *      Método encargado de setear
     *      información del personaje
     *      seleccionado.
     *
     *
     * @param message   Mensaje a mostrar en text view.
     * @param imgId     Id de imagen a mostrar.
     * @param color     Color del texto a mostrar.
     */
    public void setCharacterInfo( String message, int imgId, int color ){
        Util.showLog(TAG, message );
        mImagenSeleccionadaTextView.setImageDrawable( ContextCompat.getDrawable( CambioImagenActivity.this, imgId ) );
        mSeleccionadoTextView.setTextColor( ContextCompat.getColor( CambioImagenActivity.this, color ) );
        mSeleccionadoTextView.setText( message );

    }

}
