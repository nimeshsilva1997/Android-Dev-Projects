package com.example.lab9silva;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;



public class MainActivity extends AppCompatActivity implements SensorEventListener  {

    private SensorManager sensorManager;
    private Sensor senAccelerometer, senGyro, senMag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            senAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //Zero
            senGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            senMag = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            sensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, senGyro, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, senMag, SensorManager.SENSOR_DELAY_NORMAL);

        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(),e);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int var2) {

    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        Gson gson = new Gson();
        try{
            if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                Accel Acc = new Accel();
                Acc.setX(sensorEvent.values[0]);
                Acc.setY(sensorEvent.values[1]);
                Acc.setZ(sensorEvent.values[2]);
                String json = gson.toJson(Acc);
                System.out.println(json);
            }
            if(sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                Mag mag = new Mag();
                mag.setX(sensorEvent.values[0]);
                mag.setY(sensorEvent.values[1]);
                mag.setZ(sensorEvent.values[2]);
                String json = gson.toJson(mag);
                System.out.println(json);
            }
            if(sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                Gyro gyro = new Gyro();
                gyro.setX(sensorEvent.values[0]);
                gyro.setY(sensorEvent.values[1]);
                gyro.setZ(sensorEvent.values[2]);
                String json = gson.toJson(gyro);
                System.out.println(json);
            }
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(),e);
        }
    }
}
