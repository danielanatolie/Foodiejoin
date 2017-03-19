package com.example.danlam.foodiejoin;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private User user;
    private double userLat;
    private double userLon;
    private LatLng[] eventLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps1);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        userLat = user.getLat();
        userLon = user.getLon();

        // Add a marker to display the user and move the camera
        LatLng userLocation = new LatLng(userLat, userLon);
        mMap.addMarker(new MarkerOptions().position(userLocation).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation)); // or set to location of Vancouver

        drawEventLocationMarkers();
    }

    // Retrieves Restaurant LatLon values from firebase, and returns it in an array
    public LatLng[] retrieveEventLocationsFromFirebase(){
        //TODO

        // Event[] events = retrieveEventFromFirebase

        return null;
    }

    // Draws markers on the MapActivity using the LatLon values from eventLocations[]
    public void drawEventLocationMarkers(){
        eventLocations = retrieveEventLocationsFromFirebase();

        for(LatLng latlngs: eventLocations){
            mMap.addMarker(new MarkerOptions().position(latlngs));
        }
    }

}
