package DataModels;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CarDao {
    private static final List<Car> CARS = Arrays.asList(
            new Car("1234",new BigDecimal("120.000"),Brand.TESLA,true),
            new Car("5678",new BigDecimal("220.000"),Brand.AUDI,false),
            new Car("5688",new BigDecimal("300.000"),Brand.FERRARI,false));

    public List<Car> getAllCars(){
        return CARS;
    }

}
