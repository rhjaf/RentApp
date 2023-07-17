import DataModels.Booking;
import DataModels.Car;
import DataModels.CarDao;
import DataModels.User;
import Services.BookingService;
import Services.CarService;
import Services.EmailService;
import Services.UserService;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void printMenu(){
        System.out.println(
                        "1\uFE0F - Book Car\n" +
                        "2\uFE0F - View All User Booked Cars\n"+
                        "3\uFE0F - View All Bookings\n"+
                        "4\uFE0F - View Available Cars\n"+
                        "5\uFE0F - View Available Electronic Cars\n"+
                        "6\uFE0F - View all users\n"+
                        "7\uFE0F - Exit"
        );
        System.out.println("Enter your number: ");
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        BookingService bookingService = new BookingService();
        Scanner scanner = new Scanner(System.in);
        Integer choice = null;

        while (true){
            printMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You should enter a number!");
                continue;
            }
            switch (choice){
                case 1:
                    bookCar(userService,bookingService,scanner);
                    break;
                case 2:
                    displayAllUserBookedCars(userService,bookingService,scanner);
                    break;
                case 3:
                    allBookings(bookingService);
                    break;
                case 4:
                    displayAvailableCars(bookingService,false);
                    break;
                case 5:
                    displayAvailableCars(bookingService,true);
                    break;
                case 6:
                    displayAllUsers(userService);
                    break;
                case 7:
                    System.out.println("Thanks for your using APP! Goodbye");
                    return;
                default:
                    System.out.println("Enter a valid choice between from 1 to 7");

            }
        }

    }

    private static void displayAvailableCars(BookingService carBookingService, boolean isElectric) {
        Car[] availableCars = isElectric ? carBookingService.getAvailableElectricalCars() : carBookingService.getAvailableCars();
        if (availableCars.length == 0) {
            System.out.println("❌ No cars available for renting");
            return;
        }
        for (Car availableCar : availableCars) {
            System.out.println(availableCar);
        }
    }

    private static void displayAllUsers(UserService userService) {
        User[] users = userService.getUsers();
        if (users.length == 0) {
            System.out.println("❌ No users in the system");
            return;
        }
        for (User user : users) {
            System.out.println(user);
        }
    }
    private static void bookCar(UserService userService, BookingService carBookingService, Scanner scanner) {
        displayAvailableCars(carBookingService, false);

        System.out.println("➡️ select car reg number");
        String regNumber = scanner.nextLine();

        displayAllUsers(userService);

        System.out.println("➡️ select user id");
        String userId = scanner.nextLine();

        try {
            User user = userService.getUserById(UUID.fromString(userId));
            if (user == null) {
                System.out.println("❌ No user found with id " + userId);
            } else {
                UUID bookingId = carBookingService.bookCar(user, regNumber);
                String confirmationMessage = """
                        🎉 Successfully booked car with reg number %s for user %s
                        Booking ref: %s
                        """.formatted(regNumber, user, bookingId);
                System.out.println(confirmationMessage);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void allBookings(BookingService carBookingService) {
        Booking[] bookings = carBookingService.getBookings();
        if (bookings.length == 0) {
            System.out.println("No bookings available 😕");
            return;
        }
        for (Booking booking : bookings) {
            System.out.println("booking = " + booking);
        }
    }
    private static void displayAllUserBookedCars(UserService userService, BookingService carBookingService, Scanner scanner) {
        displayAllUsers(userService);

        System.out.println("➡️ select user id");
        String userId = scanner.nextLine();

        try{
            User user = userService.getUserById(UUID.fromString(userId));
            if (user == null) {
                System.out.println("❌ No user found with id \n" + userId);
                return;
            }

            Car[] userBookedCars = carBookingService.getUserBookedCars(user.getId());
            if (userBookedCars.length == 0) {
                System.out.printf("❌ user %s has no cars booked\n", user);
                return;
            }
            for (Car userBookedCar : userBookedCars) {
                System.out.println(userBookedCar);
            }
        }
        catch (Exception e){
            System.out.println("Can not found the specific user!\n");
        }
    }
}