import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomer_000 on 2017-03-18.
 */

public class Event{
    private User creator;
    private String restaurant;
    private String address;
    private String description;
    private List<User> users = new LinkedList<>();
    private Calendar time;

    public Event(String restaurant, String address, String description, int year, int month, int date, int hour, int minute){
    this.restaurant = restaurant;
    this.address = address;
        this.description = description;
        time.set(year,  month, date,  hour, minute);
        setCreator(null);
    }

    public void setCreator(User creator){
        this.creator = creator;
    }
    public String getRestaurant(){
    return restaurant;
}

    public String getAddress(){
        return this.address;
    }

    public void addUser(User user){
        users.add(user);

    }
}
