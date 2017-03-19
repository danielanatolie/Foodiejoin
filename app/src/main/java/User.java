import android.location.Location;
import android.location.LocationListener;

import java.util.Calendar;

/**
 * Created by tomer_000 on 2017-03-18.
 */

public class User {
    String firstname;
    String lastname;
    int age;
    Double lat;
    Double lon;

    public User(String first, String last, int age, double lat, double lon){
        firstname = first;
        lastname = last;
        this.age = age;

    }


}
