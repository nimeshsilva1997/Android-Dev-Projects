package com.example.lab11silva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor light;
    private ToggleButton toggleButton;
    private CameraManager mCameraManager;
    private String mCameraID;

    public void switchFlashlight (boolean status) {
        try {
            mCameraManager.setTorchMode(mCameraID, status);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    protected  void onResume() {
        // Register a listener for the sensor
        super.onResume();
    }

    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i("onAccuracyChanged", "LIGHT: " +sensor.getName());
    }

    public final void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            Log.i("onSensorChanged", "LIGHT: " + event.values[0]);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isFlashAvailable = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraID = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        try {
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorManager.registerListener((SensorEventListener) this, light, SensorManager.SENSOR_DELAY_NORMAL);
            toggleButton = findViewById(R.id.toggleButton);

            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    switchFlashlight(isChecked);
                }
            });
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(),e);
        }
    }
}
