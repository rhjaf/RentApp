package Services;

import DataModels.Car;
import DataModels.CarDao;

public class CarService {
    private CarDao carDao = new CarDao();
    public Car[] getAllCars(){
        return carDao.getAllCars();
    }

    public Car getCar(String regNumber) {
        for (Car car : getAllCars()) {
            if (regNumber.equals(car.getReg_number()))
                return car;
        }
        throw new IllegalStateException(String.format("Car with reg %s not found",regNumber));
    }

    public Car[] getAllElectricalCars(){
        int electric_car_count = 0;
        Car[] cars = getAllCars();
        if(cars.length==0){
            return new Car[0];
        }
        for(Car car:cars){
            if(car.isElectric())
                electric_car_count++;
        }
        if(electric_car_count==0)
            return new Car[0];
        Car[] electricCars = new Car[electric_car_count];

        int index = 0;

        for (int i = 0; i < cars.length; i++) {
            if (cars[i].isElectric()) {
                electricCars[index++] = cars[i];
            }
        }

        return electricCars;
    }

}
