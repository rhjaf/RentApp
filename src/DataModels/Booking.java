package DataModels;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Booking {


    private UUID booking_id;
    private User user;
    private Car car;
    private LocalDateTime booking_time;
    private boolean is_canceled;

    public Booking(UUID booking_id, User user_id, Car car_id, LocalDateTime booking_time, boolean is_canceled) {
        this.booking_id = booking_id;
        this.user = user_id;
        this.car = car_id;
        this.booking_time = booking_time;
        this.is_canceled = is_canceled;
    }

    public Booking(UUID booking_id, User user_id, Car car_id, LocalDateTime booking_time) {
        this(booking_id,user_id,car_id,booking_time,false);
    }

    public UUID getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(UUID booking_id) {
        this.booking_id = booking_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(LocalDateTime booking_time) {
        this.booking_time = booking_time;
    }

    public boolean isCanceled() {
        return is_canceled;
    }

    public void SetCanceled(boolean is_canceled) {
        this.is_canceled = is_canceled;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "booking_id=" + booking_id +
                ", user_id=" + user +
                ", car_id=" + car +
                ", booking_time=" + booking_time +
                ", is_canceled=" + is_canceled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return is_canceled == booking.is_canceled && Objects.equals(booking_id, booking.booking_id) && Objects.equals(user, booking.user) && Objects.equals(car, booking.car) && Objects.equals(booking_time, booking.booking_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking_id, user, car, booking_time, is_canceled);
    }
}
