package Services;

import DataModels.Car;
import DataModels.CarDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarService {
    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car> getAllCars(){
        return carDao.getAllCars();
    }

    public Car getCar(String regNumber) {
        for (Car car : getAllCars()) {
            if (regNumber.equals(car.getReg_number()))
                return car;
        }
        throw new IllegalStateException(String.format("Car with reg %s not found",regNumber));
    }

    public List<Car> getAllElectricalCars(){

        List<Car> cars = getAllCars();
        if(cars.size()==0){
            return Collections.emptyList();
        }
        List<Car> electricCars = new ArrayList<>();

        for(Car car:cars){
            if(car.isElectric())
                electricCars.add(car);
        }

        return electricCars;
    }

}
