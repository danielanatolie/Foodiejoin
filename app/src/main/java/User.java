import android.location.Location;
import android.location.LocationListener;

import java.util.Calendar;

/**
 * Created by tomer_000 on 2017-03-18.
 */

public class User {
    private String firstname;
    private String lastname;
    private int age;
    private Double lat;
    private Double lon;

    public User(String first, String last, int age, double lat, double lon){
        firstname = first;
        lastname = last;
        this.age = age;

    }


    public Double getLat(){
        return lat;
    }

    public Double getLon(){
        return lon;
    }

public Event createEvent(String restaurant, String address, String description, int year, int month, int date, int hour, int minute){
   Event event = new Event( restaurant,  address, description, year, month,  date,  hour,  minute);
    event.setCreator(this);
 return  event;
}
}
