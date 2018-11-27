package com.android.supay.test.location;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;

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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private static String TAG = MapsActivity.class.getSimpleName();

    FusedLocationProviderClient mFusedLocationClient;

    GoogleApiClient mGoogleApiClient;

    LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style));

            if (!success) {
                Util.showLog(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Util.showLog(TAG, "Can't find style. Error: " );
            e.printStackTrace();
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
                    MapsActivity.this,
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
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

    LocationCallback mLocationCallBack = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for(Location location : locationResult.getLocations()){
                Util.showLog("Main Activity", "Location: " + location.getLatitude() +
                        " " + location.getLongitude() );

                // Add a marker in Sydney and move the camera
                mMap.clear();
                LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker( new MarkerOptions()
                        .position(currentPosition)
                        .title("Current position.")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.beer))
                );

                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
            }
        }
    };

}
