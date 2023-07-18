package Services;

import DataModels.Booking;
import DataModels.BookingDao;
import DataModels.Car;
import DataModels.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingService {
    private final BookingDao bookingDao;
    private final CarService carService;

    public BookingService(BookingDao bookingDao, CarService carService) {
        this.bookingDao = bookingDao;
        this.carService = carService;
    }

    public UUID bookCar(User user, String reg_number){
        Car[] available_cars = getAvailableCars();

        if (available_cars.length == 0) {
            throw new IllegalStateException("No car available for renting");
        }

        for (Car availableCar : available_cars) {
            if (availableCar.getReg_number().equals(reg_number)) {
                Car car = carService.getCar(reg_number);
                UUID bookingId = UUID.randomUUID();
                bookingDao.book(
                        new Booking(bookingId, user, car, LocalDateTime.now())
                );
                return bookingId;
            }
        }
        throw new IllegalStateException("Already booked. car with regNumber " + reg_number);
    }
    public Car[] getUserBookedCars(UUID userId) {
        Booking[] carBookings = bookingDao.getBookings();

        int user_booked_cars = 0;

        for (Booking cb : carBookings) {
            if (cb != null && cb.getUser().getId().equals(userId)) {
                ++user_booked_cars;
            }
        }

        if (user_booked_cars == 0) {
            return new Car[0];
        }

        Car[] userCars = new Car[user_booked_cars];

        int index = 0;
        for (Booking carBooking : carBookings) {
            if (carBooking != null && carBooking.getUser().getId().equals(userId)) {
                userCars[index++] = carBooking.getCar();
            }
        }
        return userCars;
    }

    public Car[] getAvailableCars(){
        return getCars(carService.getAllCars());
    }
    public Car[] getAvailableElectricalCars(){
        return getCars(carService.getAllElectricalCars());
    }

    private Car[] getCars(Car[] cars) {

        if (cars.length == 0) {
            return new Car[0];
        }

        Booking[] carBookings = bookingDao.getBookings();

        // no bookings yet therefore all cars are available
        if (carBookings.length == 0) {
            return cars;
        }

        int availableCarsCount = 0;

        for (Car car : cars) {
            boolean booked = false;
            for (Booking carBooking : carBookings) {
                if (carBooking == null || !carBooking.getCar().equals(car)) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                ++availableCarsCount;
            }
        }

        Car[] availableCars = new Car[availableCarsCount];
        int index = 0;

        for (Car car : cars) {
            boolean booked = false;
            for (Booking carBooking : carBookings) {
                if (carBooking == null || !carBooking.getCar().equals(car)) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                availableCars[index++] = car;
            }
        }
        return availableCars;
    }



    public Booking[] getBookings(){
        Booking[] carBookings = bookingDao.getBookings();

        int number_of_bookings = 0;

        for (Booking cb : carBookings) {
            if (cb != null) {
                ++number_of_bookings;
            }
        }

        if (number_of_bookings == 0) {
            return new Booking[0];
        }

        Booking[] bookings = new Booking[number_of_bookings];

        int index = 0;
        for (Booking carBooking : carBookings) {
            if (carBooking != null) {
                bookings[index++] = carBooking;
            }
        }
        return bookings;
    }



}


