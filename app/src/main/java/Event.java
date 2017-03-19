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

    public Event(Restaurant restaurant, String address, String description, int year, int month, int date, int hour, int minute){
    this.restaurant = restaurant;
    this.address = address;
        this.description = description;
        time.set(year,  month, date,  hour, minute);
        setCreator(null);
    }

    public void setCreator(User creator){
        this.creator = creator;
    }


}
