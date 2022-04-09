package com.example.hw10;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.LocationServices;

public class MyService6 extends Service {
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    String GPS_FILTER = "matos.action.GPSFIX";
    boolean isRunning = true;
    Thread serviceThread;
    LocationManager lm;
    GPSListener myLocationListener;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "MyService6 Created", Toast.LENGTH_LONG).show();
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e("<<MyGpsService-onStart>>", "I am alive-GPS!");
        serviceThread = new Thread(new Runnable() {
            public void run() {
                getGPSFix_Version2(); // uses GPS chip provider
            }// run
        });
        serviceThread.start();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MyService6 Stop", Toast.LENGTH_LONG).show();
        super.onDestroy();

        Log.e("<<MyGpsService-onDestroy>>", "I am dead-GPS");
        try {
            lm.removeUpdates(myLocationListener);
            isRunning = false;
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void getGPSFix_Version2() {
        try
        {
            Looper.prepare();
            // try to get your GPS location using the LOCATION.SERVIVE provider
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // This listener will catch and disseminate location updates
            myLocationListener = new GPSListener();
            // define update frequency for GPS readings
            long minTime = 2000; // 2 seconds
            float minDistance = 5; // 5 meter
            // request GPS updates
            if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION )
                    != PackageManager.PERMISSION_GRANTED )
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,
                    myLocationListener);
            Looper.loop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {

            // capture location data sent by current provider
            double latitude = location.getLatitude(), longitude = location.getLongitude();
            // assemble data bundle to be broadcasted
            Intent myFilteredResponse = new Intent("matos.action.GPSFIX");
            myFilteredResponse.putExtra("latitude", latitude);
            myFilteredResponse.putExtra("longitude", longitude);
            myFilteredResponse.putExtra("provider", location.getProvider());
            Log.e(">>GPS_Service<<", "Lat:" + latitude + " lon:" + longitude);
            // send the location data out
            sendBroadcast(myFilteredResponse);
        }
        public void onProviderDisabled(String provider) { }
        public void onProviderEnabled(String provider) { }
        public void onStatusChanged(String provider, int status, Bundle extras) { }
    };// GPSListener clas
}
