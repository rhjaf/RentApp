package DataModels;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class
Car {

    private String reg_number;
    private BigDecimal rental_price_per_day;
    private Brand brand;
    private boolean is_electric;


    public Car(String reg_number, BigDecimal rental_price_per_day, Brand brand, boolean is_electric) {
        this.reg_number = reg_number;
        this.rental_price_per_day = rental_price_per_day;
        this.brand = brand;
        this.is_electric = is_electric;
    }


    public String getReg_number() {
        return reg_number;
    }

    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }

    public BigDecimal getRental_price_per_day() {
        return rental_price_per_day;
    }

    public void setRental_price_per_day(BigDecimal rental_price_per_day) {
        this.rental_price_per_day = rental_price_per_day;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isElectric() {
        return is_electric;
    }

    public void setElectric(boolean is_electric) {
        this.is_electric = is_electric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return is_electric == car.is_electric && Objects.equals(reg_number, car.reg_number) && Objects.equals(rental_price_per_day, car.rental_price_per_day) && Objects.equals(brand, car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reg_number, rental_price_per_day, brand, is_electric);
    }

    @Override
    public String toString() {
        return "Car{" +
                "reg_number='" + reg_number + '\'' +
                ", rental_price_per_day=" + rental_price_per_day +
                ", brand=" + brand +
                ", is_electric=" + is_electric +
                '}';
    }
}
