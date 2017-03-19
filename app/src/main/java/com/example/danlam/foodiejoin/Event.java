package com.example.danlam.foodiejoin;

import com.example.danlam.foodiejoin.User;
import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomer_000 on 2017-03-18.
 */

public class Event{
    User creator;
    String address;
    String description;
    double lat;
    double lng;
    List<User> users = new LinkedList<>();

    public Event(String address, String description, int year, int month, int date, int hour, int minute, double lat, double lng){
        this.address = address;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        setCreator(null);
    }
    public Event(){

    }

    public void setCreator(User creater) {
        this.creator = creater;
    }
}
