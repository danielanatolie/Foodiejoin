package com.example.danlam.foodiejoin;

import com.example.danlam.foodiejoin.User;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomer_000 on 2017-03-18.
 */

public class Event{
    User creator;
    Restaurant restaurant;
    String address;
    String description;
    List<User> users = new LinkedList<>();
    Calendar time;

    public Event(Restaurant restaurant, String address, String description, Calendar time){
    this.restaurant = restaurant;
    this.address = address;
        this.description = description;
        this.time =
    }
}
