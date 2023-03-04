package edu.umb.cs681.hw02;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CarTest {

    public static ArrayList<Car> usedCarData;

    @BeforeAll
    public static void createdata() {
        usedCarData = new TestFixtureInitializer().createCarData();
    }

    ;

    @Test
    public void verifyAverageTest() {
        double expected_average = 11750.0;
        Car.CarPriceResultHolder resultHolder = usedCarData.stream()
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

        double actual_average = resultHolder.getAverage();
        assertEquals(expected_average, actual_average);
    }
}