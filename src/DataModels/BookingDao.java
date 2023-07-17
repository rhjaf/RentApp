package DataModels;

public class BookingDao {

    private static final Booking[] bookings;

    static{
        bookings = new Booking[10];
    }

    public Booking[] getBookings(){
        return bookings;
    }

    public void book(Booking booking) {
        int nextFreeIndex = -1;

        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] == null) {
                nextFreeIndex = i;
                break;
            }
        }
        if (nextFreeIndex > -1) {
            bookings[nextFreeIndex] = booking;
            return;
        }
        Booking[] bigBookings = new Booking[bookings.length + 10];
        for (int i = 0; i < bookings.length; i++)
            bigBookings[i] = bookings[i];
        bigBookings[bookings.length] = booking;
    }

}
