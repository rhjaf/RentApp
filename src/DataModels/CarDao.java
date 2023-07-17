package DataModels;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDao {
    private static final Car[] CARS = {
            new Car("1234",new BigDecimal("120.000"),Brand.TELSA,true),
            new Car("5678",new BigDecimal("220.000"),Brand.AUDI,false),
            new Car("5688",new BigDecimal("300.000"),Brand.FERRARI,false)
    };

    public Car[] getAllCars(){
        return CARS;
    }

}
