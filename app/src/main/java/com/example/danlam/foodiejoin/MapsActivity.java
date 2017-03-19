package com.example.danlam.foodiejoin;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private User user;
    private double userLat=0;
    private double userLon=0;
    private LatLng[] eventLocations;
    ArrayList<Event> events = new ArrayList<>();

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

        // Add a marker to display the user and move the camera
        LatLng userLocation = new LatLng(userLat, userLon);
        mMap.addMarker(new MarkerOptions().position(userLocation).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation)); // or set to location of Vancouver

        drawEventLocationMarkers();
    }

    // Retrieves Restaurant LatLon values from firebase, and returns it in an array
    public LatLng[] retrieveEventLocationsFromFirebase(){
        //TODO
        return null;
    }


    public void loadEvents(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("events");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                events.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    events.add(postSnapshot.getValue(Event.class));
                }
                System.out.println("event " + events.get(0).description);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("not read");

            }
        });
    }
    // Draws markers on the MapActivity using the LatLon values from eventLocations[]
    public void drawEventLocationMarkers(){
        loadEvents();

        for(Event event: events){
            LatLng latLng = new LatLng(event.lat,event.lng);
            mMap.addMarker(new MarkerOptions().position(latLng));
        }
    }

}
