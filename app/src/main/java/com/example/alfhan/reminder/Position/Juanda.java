package com.example.alfhan.reminder.Position;

import com.example.alfhan.reminder.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Alfhan on 11/7/2018.
 */

public class Juanda extends MainActivity {

    private GoogleMap mMap;

    public void JuandaPos(){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.171280, 106.858290))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.171280, 106.858290))
                .title("Phone Location")
        );
    }
}
