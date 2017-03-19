package com.example.danlam.foodiejoin;

import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private User user;
    private double userLat=0;
    private double userLon=0;
    private LatLng[] eventLocations;
    String eventDescription="";
    ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //loadEvents();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                System.out.println("info window clicked");
                for(Event event:events){
                    if (marker.getTitle().equals(event.description)){
                        System.out.println("matched event");
                        showDialog(event);
                    }
                }

            }
        });
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
                    Event event = postSnapshot.getValue(Event.class);
                    events.add(event);
                        System.out.println("LAT " +event.lat + " long " + event.lng);
                        LatLng latLng = new LatLng(event.lat,event.lng);
                        mMap.addMarker(new MarkerOptions().position(latLng).title(event.description));
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

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }

    public void showDialog(Event event){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        eventDescription=event.description;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(event.description)
                .setTitle("join event?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference eventsRef = database.getReference("events");
                eventsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Event eventFromList = postSnapshot.getValue(Event.class);
                            if(eventDescription.equals(eventFromList.description)){
                                DataSnapshot users = postSnapshot.child("users");
                                users.child(Long.toString(users.getChildrenCount())).getRef().setValue(MainActivity.userName);
                            }
                        }
                        System.out.println("event " + events.get(0).description);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("not read");
                    }
                });
                //events.put("test event", test);
                //eventsRef.setValue(events);
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
