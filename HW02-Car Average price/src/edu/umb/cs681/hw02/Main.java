package edu.umb.cs681.hw02;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Car> cardata = new ArrayList<>();
        cardata.add(new Car("Hyundai-1", 2000, 8000, 53));
        cardata.add(new Car("Honda-2", 2001, 9000, 67));
        cardata.add(new Car("Maruti-3", 2002, 10000, 98));
        cardata.add(new Car("Mercedes-4", 2004, 20000, 72));

        Car.CarPriceResultHolder resultHolder = cardata.stream()
                .map(car -> car.getPrice())
                .reduce(new Car.CarPriceResultHolder(),
                        (result, price) -> {
                            result.accumulate(price);
                            return result;
                        },
                        (result1, result2) -> {
                            result1.combine(result2);
                            return result1;
                        });

        double averagePrice = resultHolder.getAverage();
        System.out.println("Average car price: " + averagePrice);
    }
}
