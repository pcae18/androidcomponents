package com.android.supay.test.location;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.supay.test.R;
import com.android.supay.test.util.KeyDefinitions;
import com.android.supay.test.util.Util;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationSambleActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.textViewLocation)
    TextView textViewLocation;

    @BindView(R.id.progressBar)
    ProgressBar progressBarLocation;

    FusedLocationProviderClient mFusedLocationClient;

    GoogleApiClient mGoogleApiClient;

    LocationRequest mLocationRequest;

    private static String TAG = LocationSambleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_samble);
        ButterKnife.bind(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        buildGoogleApliClient();
    }

    private void buildGoogleApliClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.reconnect();
    }

    /**
     *
     * Se verifican los servicios de Google Service.
     */
    @Override
    protected void onResume() {
        super.onResume();
        int response = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if(response != ConnectionResult.SUCCESS){
            Util.showLog(TAG, "Google Play Service Not Available");
            GoogleApiAvailability.getInstance().getErrorDialog(
                    LocationSambleActivity.this,
                    response,
                    1
            ).show();
        }else{
            if(mGoogleApiClient != null && mFusedLocationClient != null){
                requestLocationUpdates();
            }else{
                buildGoogleApliClient();
            }
            Util.showLog(TAG, "Google play service available");
        }
    }

    /**
     *
     *  Condiciones de peticiones para
     *  solicitar ubicación.
     *
     */
    private void requestLocationUpdates() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallBack,
                    Looper.myLooper());

        }else{
            //Dialogo de solicitud de permisos.
            ActivityCompat.requestPermissions( this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    KeyDefinitions.LOCATION_PERMISSION_ID);
        }
    }

    /**
     *
     *  Quitar solicitudes de actualización.
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(mFusedLocationClient != null){
            mFusedLocationClient.removeLocationUpdates(mLocationCallBack);
        }
    }

    /**
     *
     *  Desconectar google api client.
     *
     */
    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }


    LocationCallback mLocationCallBack = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for(Location location : locationResult.getLocations()){
                Util.showLog("Main Activity", "Location: " + location.getLatitude() +
                " " + location.getLongitude() );
                textViewLocation.setText(Util.getEmojiByUnicode(0x1F680)
                        + Util.getEmojiByUnicode(0x1F680) + "Location: "
                        + location.getLatitude() + location.getLongitude()
                );
                progressBarLocation.setVisibility(View.GONE);
            }
        }
    };

    /**
     *
     *  Método que se llama después de solicitar
     *  permisos.
     *
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case KeyDefinitions.LOCATION_PERMISSION_ID:{
                //If request is cancelled, the result arrays are empty.
                if(grantResults.length> 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted, yay! Do the
                    //location-related task you need to do.
                    if(ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED
                    ){
                        onResume();
                    }else{
                        //Util.showPop();
                        AlertDialog.Builder builder = new AlertDialog.Builder(LocationSambleActivity.this);
                        builder.setTitle("No tienes permisos necesarios")
                                .setMessage("No tienes permisos necesarios")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builder.show();
                    }
                }else{

                }
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Util.showLog(TAG, "on Connected ");
        requestLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Util.showLog(TAG, "onConnectionSuspend");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Util.showLog(TAG, "onConnectionFailed");

    }
}
