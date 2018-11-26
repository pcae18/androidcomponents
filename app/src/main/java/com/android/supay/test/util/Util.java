package com.android.supay.test.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.supay.test.R;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase de métodos para ayuda
 * del curso
 *
 */
public class Util {

    /**
     *      Método encargado de mostrar
     *      logs en el sistema.
     */
    public static void showLog( String TAG, String message ){
        Log.e( TAG, message );

    }

    /**
     *      Método encargado de mostrar u
     *  ocultar un {@link View}.
     *
     * @author  Paulo_Angeles.
     * @param component   {@link View} a  mostrar/ocultar.
     */
    public static void showHideViewComponent( View component ){
        if( component.getVisibility() == View.VISIBLE ){
            component.setVisibility( View.GONE );
        }else{
            component.setVisibility( View.VISIBLE );

        }
    }


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
    public static void showSnackBar(String message, View parentLayout){
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
    public static void showToast(String message, Context context){
        Toast.makeText( context, message, Snackbar.LENGTH_LONG )
                .show();
    }

    /**
     *      Método encargado de validar
     *  un correo electrónico.
     *
     * @author  Paulo_Angeles.
     *
     * @param  email           Correo a validar.
     * @return Boolean          Respuesta de Validación.
     */
    public static Boolean validEmail( String email ){
        Matcher matcher = Definitions.VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    /**
     *      Método encargado de validar
     *  una contraseña.
     *
     * @author  Paulo_Angeles.
     *
     * @param  password         Contraseña a validar.
     * @return Boolean          Respuesta de Validación.
     */
    public static Boolean validPassword( String password ){
        return password.length() < Definitions.MAX_SIZE_PASSWORD && password.length() > Definitions.MIN_SIZE_PASSWORD;
    }

    /**
     *
     * Método encargado de cargar
     * imagen a un componente
     *
     * @author Paulo_Angeles.
     *
     * @param src       Ruta de la imagen.
     * @param context   Contexto de la aplicación.
     * @param imageView {@link ImageView} donde se mostrará la imagen.
     * Nota:Alternativa Picasso, pero es más robusta.
     */
    public static void setUpImageView(String src, Context context, ImageView imageView){
        /**CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable( context);
        circularProgressDrawable.setStrokeWidth(20f);
        circularProgressDrawable.setCenterRadius(60f);
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent ),
                PorterDuff.Mode.SRC_ATOP );
        circularProgressDrawable.start();*/

        Glide.with( context )
                .load( src )
                .centerCrop()
                .crossFade(5000)
                .into(imageView);
    }

    /**
     *
     * @param activity
     * @param aClass
     * @param parameters
     */
    public static void changeActivity( Activity activity, Class aClass, HashMap<String, String> parameters ){
        Intent intent = new Intent( activity, aClass );
        for(Map.Entry<String, String> entry : parameters.entrySet() ){
            intent.putExtra( entry.getKey( ), entry.getValue( ) );

        }
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     *
     * @param activity
     * @param aClass
     * @param parameters
     */
    public static void changeActivity( Activity activity, Class aClass, HashMap<String, String> parameters, boolean finish ){
        Intent intent = new Intent( activity, aClass );
        for(Map.Entry<String, String> entry : parameters.entrySet() ){
            intent.putExtra( entry.getKey( ), entry.getValue( ) );

        }
        activity.startActivity(intent);
        if(finish){
            activity.finish();
        }
    }

    /**
     * Método que genera números random.
     * @return
     */
    public static int getRandomNumber(){
        return  (int)(Math.random()*((3-1)+1))+1;
    }


    /**
     * Método que genera id random.
     * @return
     */
    public static int getRandomID(){
        return  (int)(Math.random()*((50000-1)+1))+1;
    }

    public static String setRandomImage(){
        switch (Util.getRandomNumber()){
            case 1:
                return "http://weddingwoof.com/wp-content/uploads/2012/06/dogstore-1.jpg";
            case 2:
                return "https://barkpost.com/wp-content/uploads/2014/11/lavadogshawaii.jpg";
            case 3:
                return "https://barkpost.com/wp-content/uploads/2014/11/lavadogshawaii.jpg";
            case 4:
                return Definitions.SRC_IMG_ADVENGERS_IRON_MAN;
            case 5:
                return Definitions.SRC_IMG_ADVENGERS_MAPACHE;
        }
        return "";
    }
}
