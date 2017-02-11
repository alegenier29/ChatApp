package com.yoval.community.localisation;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.yoval.community.model.UserLocation;

/**
 * Created by Alejandro on 2017-01-19.
 */

public class CustomizedLocationListener implements LocationListener {

    private static Context context;
    public CustomizedLocationListener(Context c) {
        context = c;
    }

    @Override
    public void onLocationChanged(Location location) {

        if (location != null){
            int duration = Toast.LENGTH_SHORT;
            Toast toastLat = Toast.makeText(context, "Longitude=" + location.getLongitude(), duration);
            Toast toastLon = Toast.makeText(context, "Latitude=" + location.getLatitude(), duration);
            toastLat.setGravity(Gravity.TOP| Gravity.CENTER, 0, 0);
            toastLat.show();
            toastLat.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
            toastLon.show();

            if(FirebaseAuth.getInstance().getCurrentUser() != null && location != null)
            FirebaseDatabase.getInstance()
                    .getReference()
                    //.push()
                    .setValue(new UserLocation(FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getUid(), location.getLatitude(),
                            location.getLongitude()));
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
