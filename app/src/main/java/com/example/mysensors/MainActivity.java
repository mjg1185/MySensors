/*
  Application name: My Sensors
  Author: Martin James Gasiorowski II
  Purpose: In order to demonstrate programming capabilities as they pertain to the reading of
  sensor data on an Android device and displaying them to the user. This application is for educational
  purposes only and is not intended for commercial distribution. All rights reserved.
  **/

package com.example.mysensors;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //initialize shared objects
    //tags
    private static final String TAG = "MainActivity";
    //sensors
    private SensorManager sensorManager;
    private Sensor accelerometer, gyro, magno, light, thermo, pressure, humid;
    //GUI
    TextView accelXValue, accelYValue, accelZValue, gyroXValue, gyroYValue, gyroZValue,
        magXValue, magYValue, magZValue, lightValue, tempValue, pressureValue, humidityValue;

    /**
     * Initializes all GUI objects, sensors, and listeners
     * **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create dialog box for app info and display to user


        initGUI(); //initialize all GUI components
        initSensors(); //initialize all sensor components



    }

    /**
     * Initializes all GUI components and connects them to Java components
     * Param: none
     * Return: void
     * **/
    public void initGUI(){
        //initialize TextViews
        //accel
        accelXValue = findViewById(R.id.accelXValue);
        accelYValue = findViewById(R.id.accelYValue);
        accelZValue = findViewById(R.id.accelZValue);
        //gyro
        gyroXValue = findViewById(R.id.gyroXValue);
        gyroYValue = findViewById(R.id.gyroYValue);
        gyroZValue = findViewById(R.id.gyroZValue);
        //magnet
        magXValue = findViewById(R.id.magXValue);
        magYValue = findViewById(R.id.magYValue);
        magZValue = findViewById(R.id.magZValue);
        //weather
        lightValue = findViewById(R.id.lightValue);
        tempValue = findViewById(R.id.tempValue);
        pressureValue = findViewById(R.id.pressureValue);
        humidityValue = findViewById(R.id.humidityValue);
    }

    /**
     * Initializes all sensors and connects them to Java components.
     * Check that sensors are available on device and display N/A if not.
     * Param: none
     * Return: void
     * **/
    public void initSensors(){
        //initialize sensor data stream
        Log.d(TAG, "initSensors: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //init accelerometer
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer != null){
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "initSensors: Registered accelerometer listener");
        }else{
            accelXValue.setText("Accelerometer N/A");
            accelYValue.setText("Accelerometer N/A");
            accelZValue.setText("Accelerometer N/A");
            Log.d(TAG, "initSensors: Could not initialize accelerometer");
        }

        //init gyro
        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyro != null){
            sensorManager.registerListener(MainActivity.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "initSensors: Registered gyroscope listener");
        }else{
            gyroXValue.setText("Gyroscope N/A");
            gyroYValue.setText("Gyroscope N/A");
            gyroZValue.setText("Gyroscope N/A");
            Log.d(TAG, "initSensors: Could not initialize gyroscope");
        }

        //init magnetometer
        magno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(magno != null){
            sensorManager.registerListener(MainActivity.this, magno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "initSensors: Registered magnetometer listener");
        }else{
            magXValue.setText("Magnometer N/A");
            magYValue.setText("Magnometer N/A");
            magZValue.setText("Magnometer N/A");
            Log.d(TAG, "initSensors: Could not initialize magnamometer");
        }

        //init light meter
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light != null){
            sensorManager.registerListener(MainActivity.this, light, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "initSensors: Registered light sensor listener");
        }else{
            lightValue.setText("Light sensor N/A");
            Log.d(TAG, "initSensors: Could not initialize light sensor");
        }

        //init thermometer
        thermo = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(thermo != null){
            sensorManager.registerListener(MainActivity.this, thermo, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "initSensors: Registered thermometer listener");
        }else{
            tempValue.setText("Thermometer N/A");
            Log.d(TAG, "initSensors: Could not initialize thermometer");
        }

        //init barometer
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(pressure != null){
            sensorManager.registerListener(MainActivity.this, pressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "initSensors: Registered barometer listener");
        }else{
            pressureValue.setText("Barometer N/A");
            Log.d(TAG, "initSensors: Could not initialize barometer");
        }

        //init humidity sensor
        humid = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(humid != null){
            sensorManager.registerListener(MainActivity.this, humid, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "initSensors: Registered humidity listener");
        }else{
            humidityValue.setText("Humidity sensor N/A");
            Log.d(TAG, "initSensors: Could not initialize humidity sensor");
        }
    }

    /**
     * Receives a changed event from the Sensor Manager, parses the event, and updates
     * the appropriate text view
     * Param: SensorEvent triggered by system
     * Return: void
     * **/
    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //log changes
            Log.d(TAG, "onSensorChanged: Accel: X: " + sensorEvent.values[0] + " Y: " + sensorEvent.values[1]
                    + " Z: " + sensorEvent.values[2]);
            //display values on TextViews
            accelXValue.setText("Accel(X): " + sensorEvent.values[0]);
            accelYValue.setText("Accel(Y): " + sensorEvent.values[1]);
            accelZValue.setText("Accel(Z): " + sensorEvent.values[2]);
        }else if(sensor.getType() == Sensor.TYPE_GYROSCOPE){
            //log changes
            Log.d(TAG, "onSensorChanged: Gyro X: " + sensorEvent.values[0] + " Y: " + sensorEvent.values[1]
                    + " Z: " + sensorEvent.values[2]);
            //display values on TextViews
            gyroXValue.setText("Gyro(X): " + sensorEvent.values[0]);
            gyroYValue.setText("Gyro(Y): " + sensorEvent.values[1]);
            gyroZValue.setText("Gyro(Z): " + sensorEvent.values[2]);
        }else if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            //log changes
            Log.d(TAG, "onSensorChanged: Magnet: X: " + sensorEvent.values[0] + " Y: " + sensorEvent.values[1]
                    + " Z: " + sensorEvent.values[2]);
            //display values on Textviews
            magXValue.setText("Magnet(X): " + sensorEvent.values[0]);
            magYValue.setText("Magnet(Y): " + sensorEvent.values[1]);
            magZValue.setText("Magnet(Z): " + sensorEvent.values[2]);
        }else if(sensor.getType() == Sensor.TYPE_LIGHT){
            //log changes
            Log.d(TAG, "onSensorChanged: Light: " + sensorEvent.values[0]);
            //display values on TextViews
            lightValue.setText("Light: " + sensorEvent.values[0]);
        }else if(sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            //log changes
            Log.d(TAG, "onSensorChanged: Thermometer: " + sensorEvent.values[0]);
            //display values on TextViews
            tempValue.setText("Temperature: " + sensorEvent.values[0]);
        }else if(sensor.getType() == Sensor.TYPE_PRESSURE){
            //log changes
            Log.d(TAG, "onSensorChanged: Pressure: " + sensorEvent.values[0]);
            //display values on TextViews
            pressureValue.setText("Pressure: " + sensorEvent.values[0]);
        }else if(sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            //log changes
            Log.d(TAG, "onSensorChanged: Humidity: " + sensorEvent.values[0]);
            //display values on TextViews
            humidityValue.setText("Humidity: " + sensorEvent.values[0]);
        }

    }
    
    /**
     * This function is a required declaration for the SensorEvent listener.
     * This application should never call this function.
     * **/
    @Override
    public void onAccuracyChanged(Sensor sensor, int i){
        //do nothing
    }

}
