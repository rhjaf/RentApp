package Services;

import DataModels.Booking;
import DataModels.BookingDao;
import DataModels.Car;
import DataModels.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import java.util.List;

public class BookingService {
    private final BookingDao bookingDao;
    private final CarService carService;

    public BookingService(BookingDao bookingDao, CarService carService) {
        this.bookingDao = bookingDao;
        this.carService = carService;
    }

    public UUID bookCar(User user, String reg_number){
        List<Car> available_cars = getAvailableCars();

        if (available_cars.isEmpty()) {
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
    public List<Car> getUserBookedCars(UUID userId) {
        List<Booking> carBookings = bookingDao.getBookings();
        List<Car> userCars = new ArrayList<>();

        for (Booking cb : carBookings) {
            if (cb != null && cb.getUser().getId().equals(userId)) {
               userCars.add(cb.getCar());
            }
        }

        return userCars;
    }

    public List<Car> getAvailableCars(){
        return getCars(carService.getAllCars());
    }
    public List<Car> getAvailableElectricalCars(){
        return getCars(carService.getAllElectricalCars());
    }

    private List<Car> getCars(List<Car> cars) {

        if (cars.isEmpty()) {
            return Collections.emptyList();
        }

        List<Booking> carBookings = bookingDao.getBookings();
        if (carBookings.isEmpty()) {
            return cars;
        }

        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            boolean booked = false;
            for (Booking carBooking : carBookings) {
                if (carBooking == null || !carBooking.getCar().equals(car)) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }



    public List<Booking> getBookings(){
        return bookingDao.getBookings();
    }



}


