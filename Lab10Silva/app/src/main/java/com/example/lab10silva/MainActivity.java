package com.example.lab10silva;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import android.Manifest;

public class MainActivity extends AppCompatActivity {

    private LocationManager lm;
    private  Location location;

    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private static final int LOCATION_REQUEST=1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,10, locationListener);
        } catch (SecurityException se) {
            Context context = getApplicationContext();
            CharSequence text ="Security Exception";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);
        }
    }
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            try {
                GPS gps = new GPS();
                gps.setLongitude((float)location.getLongitude());
                gps.setLatitude((float)location.getLatitude());
                gps.setAltitude((float)location.getAltitude());

                //Convert POJO Plain Old Java Object to JSON string
                Gson gson = new Gson();
                String json = gson.toJson(gps);
                Log.i("Location Listener", json);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            GPS gps = new GPS();
            gps.setLongitude((float)location.getLongitude());
            gps.setLatitude((float)location.getLatitude());
            gps.setAltitude((float)location.getAltitude());

            //Convert POJO Plain Old Java Object to JSON string
            Gson gson = new Gson();
            String json = gson.toJson(gps);
            Log.i("On Status Changed", json);

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
