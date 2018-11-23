package com.android.supay.test;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import com.android.supay.test.util.Util;

/**
 * Actividad principal
 * de la aplicación.
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView mImageView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.showLog(TAG, "On Create");
        mImageView = findViewById(R.id.imageView);
        mTextView = findViewById(R.id.txtView);
        setUpView();
        readAssets( "textExample.txt" );
    }

    /**
     * Método encargado
     * de leer archivos.
     *
     */
    private void readAssets( String fileName ) {
        try{
            Util.showLog( TAG,"Iniciando la lectura del archivo : " + fileName );
            InputStream is = getAssets().open( fileName );
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String textFile = new String( buffer );
            Util.showLog(TAG, textFile );

        }catch (Exception e){
            Util.showLog( TAG,"Ocurrió un error en la lectura del archivo : " + fileName );
            e.printStackTrace();
        }
    }

    /**
     * Método encargado de
     * setear valores a la vista
     */
    private void setUpView(){
        Util.showLog( TAG,"Cargando atributos a la vista");
        mTextView.setText( R.string.text_string );
        mTextView.setTextColor(ContextCompat.getColor( MainActivity.this, R.color.colorText ) );
        mTextView.setBackgroundResource( R.color.colorBackgroundText );
        mImageView.setImageDrawable( ContextCompat.getDrawable( MainActivity.this, R.drawable.r2d2 ) );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Util.showLog( TAG,"On Start");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.showLog( TAG,"On Resume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Util.showLog( TAG,"On Pause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Util.showLog( TAG,"On Stop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Util.showLog( TAG,"On Destroy");

    }

}
