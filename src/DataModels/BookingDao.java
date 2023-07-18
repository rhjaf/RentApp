package DataModels;

import java.util.ArrayList;
import java.util.List;

public class BookingDao {

    private static final List<Booking> bookings;

    static{
        bookings = new ArrayList<Booking>();
    }

    public List<Booking> getBookings(){
        return bookings;
    }

    public void book(Booking booking) {
        bookings.add(booking);

    }

}
