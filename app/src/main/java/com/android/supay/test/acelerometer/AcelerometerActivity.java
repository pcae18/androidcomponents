package com.android.supay.test.acelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.supay.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private float lastX, lastY, lastZ;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float deltaXMax = 0;
    private float deltaYMax = 0;
    private float deltaZMax = 0;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;

    @BindView(R.id.currentX) TextView currentX;
    @BindView(R.id.currentY) TextView currentY;
    @BindView(R.id.currentZ) TextView currentZ;
    @BindView(R.id.maxX) TextView maxX;
    @BindView(R.id.maxY) TextView maxY;
    @BindView(R.id.maxZ) TextView maxZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometer);
        ButterKnife.bind(this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if( sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null ){
            //success we have an accelerometer
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener( this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            //fail we dont have an accelerometer
        }
    }

    //onResume() register the accelerometer for listening the events.
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause() unregister the accelerometer for stop listening  the events.
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        //clean current values
        displayCleanValues();
        //display the current x,y,z accelerometer values
        displayCurrentValues();
        //display the max x,y,z accelerometer values
        displayMaxValues();

        //get the change of the x,y,z values of the acclerometer
        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);

        //if the change is below 2, it is just plain note
        if(deltaX < 2)
            deltaX = 0;
        if(deltaY <2)
            deltaY = 0;
        if(deltaZ < 2)
            deltaZ = 0;

        //set the last know values of x,y,z
        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];
    }

    //display the max x,y,z accelerometer values
    private void displayMaxValues() {
        if(deltaX > deltaXMax){
            deltaXMax = deltaX;
            maxX.setText(String.valueOf(deltaXMax));
        }
        if(deltaY > deltaYMax){
            deltaYMax = deltaY;
            maxY.setText(String.valueOf(deltaYMax));
        }
        if(deltaZ > deltaZMax){
            deltaZMax = deltaZ;
            maxZ.setText(String.valueOf(deltaZMax));
        }
    }

    public void displayCleanValues(){
        currentX.setText("0.0");
        currentY.setText("0.0");
        currentZ.setText("0.0");
    }

    //display the current x,y,z accelerometer values
    public void displayCurrentValues(){
        currentX.setText(String.valueOf(deltaX));
        currentY.setText(String.valueOf(deltaY));
        currentZ.setText(String.valueOf(deltaZ));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
