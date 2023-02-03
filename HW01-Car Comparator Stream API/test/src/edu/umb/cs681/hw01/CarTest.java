package edu.umb.cs681.hw01;
import static org.junit.jupiter.api.Assertions.*;
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
    public static void createdata(){
      usedCarData = new TestFixtureInitializer().createCarData();
    };

    @Test
    public void verifyMileageComparatorNaturalOrderTest() {
        ArrayList<Float> expected = new ArrayList<>(Arrays.asList(5300f, 6773f, 7200f, 9812f));
        ArrayList<Float> actual = new ArrayList<>();
        var byYearAscending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.toList());
        byYearAscending.forEach(c -> actual.add(c.getMileage()));
        assertEquals(actual, expected);
    }

    @Test
    public void verifyHighLowAvgMileageTest(){

        int expected_high = 9812;
        int expected_low = 5300;
        double expected_avg = 7271.25;
        var byYearAscending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.toList());
        double actual_avg = byYearAscending.stream()
                .mapToInt(c -> (int) c.getMileage())
                .average()
                .orElse(0.0);
        int actual_high = (int) usedCarData.stream()
                .mapToDouble(Car::getMileage)
                .max()
                .orElse(0);
        int actual_low = (int) usedCarData.stream()
                .mapToDouble(Car::getMileage)
                .min()
                .orElse(0);
        assertEquals(expected_high, actual_high);
        assertEquals(expected_avg, actual_avg);
        assertEquals(expected_low, actual_low);
    }
    @Test
    public void verifyMileageComparatorReverseOrderTest() {
        ArrayList<Float> expected = new ArrayList<>(Arrays.asList(9812f,7200f,6773f,5300f));
        ArrayList<Float> actual = new ArrayList<>();
        var byYeardescending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        byYeardescending.forEach(c -> actual.add(c.getMileage()));
        assertEquals(actual, expected);
    }

    @Test
    public void verifyPriceComparatorNaturalOrderTest(){
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(8000, 9000, 10000, 20000));
        ArrayList<Integer> actual = new ArrayList<>();
        var byYearAscending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.toList());
        byYearAscending.forEach(c -> actual.add(c.getPrice()));
        assertEquals(actual, expected);
    }

    @Test
    public void verifyHighLowAvgPriceTest(){
        int expected_high = 20000;
        int expected_low = 8000;
        double expected_avg = 11750.0;
        var byYearAscending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.toList());
        double actual_avg = byYearAscending.stream()
                .mapToInt(c -> (int) c.getPrice())
                .average()
                .orElse(0.0);
        int actual_high = (int) usedCarData.stream()
                .mapToDouble(Car::getPrice)
                .max()
                .orElse(0);
        int actual_low = (int) usedCarData.stream()
                .mapToDouble(Car::getPrice)
                .min()
                .orElse(0);
        assertEquals(expected_high, actual_high);
        assertEquals(expected_avg, actual_avg);
        assertEquals(expected_low, actual_low);
    }

    @Test
    public void verifyPriceComparatorReverseOrderTest(){
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(20000,10000,9000,8000));
        ArrayList<Integer> actual = new ArrayList<>();
        var byYearAscending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        byYearAscending.forEach(c -> actual.add(c.getPrice()));
        assertEquals(actual, expected);
    }

    @Test
    public void verifyYearComparatorNaturalOrderTest(){
        ArrayList<Integer> actual = new ArrayList<>();
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2000, 2001, 2002, 2004));
        var byYearAscending = usedCarData.stream()
                .sorted(Comparator.comparingInt(Car::getYear))
                .collect(Collectors.toList());
        byYearAscending.forEach(c -> actual.add(c.getYear()));
        assertEquals(actual, expected);
    }

    @Test
    public void verifyYearComparatorReverseOrderTest(){
        ArrayList<Integer> actual = new ArrayList<>();
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2004,2002,2001,2000));
        var byYeardescending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        byYeardescending.forEach(c -> actual.add(c.getYear()));
        assertEquals(actual, expected);
    }

    @Test
    public void verifyLowHighAvgYearTest(){
        int expected_high = 2004;
        int expected_low = 2000;
        double expected_avg = 2001.75;
        var byYearAscending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.toList());
        double actual_avg = byYearAscending.stream()
                .mapToInt(c -> (int) c.getYear())
                .average()
                .orElse(0.0);
        int actual_high = (int) usedCarData.stream()
                .mapToDouble(Car::getYear)
                .max()
                .orElse(0);
        int actual_low = (int) usedCarData.stream()
                .mapToDouble(Car::getYear)
                .min()
                .orElse(0);
        assertEquals(expected_high, actual_high);
        assertEquals(expected_avg, actual_avg);
        assertEquals(expected_low, actual_low);
    }
    @Test
    public void verifyDominationComparatorNaturalOrderTest() {

        ArrayList<Integer> actual = new ArrayList<>();
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0, 0, 2, 3));
        usedCarData.forEach(car -> car.dominationCount(usedCarData, car));
        var byYearAscending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.toList());
        byYearAscending.forEach(c -> actual.add(c.getDominationCount()));
        assertEquals(actual, expected);

    }

    @Test
    public void verifyLowHighAvgDominationTest(){
        int expected_high = 3;
        int expected_low = 0;
        double expected_avg = 1.25;
        usedCarData.forEach(car -> car.dominationCount(usedCarData, car));
        var byYearAscending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.toList());
        double actual_avg = byYearAscending.stream()
                .mapToInt(c -> (int) c.getDominationCount())
                .average()
                .orElse(0.0);
        int actual_high = (int) usedCarData.stream()
                .mapToDouble(Car::getDominationCount)
                .max()
                .orElse(0);
        int actual_low = (int) usedCarData.stream()
                .mapToDouble(Car::getDominationCount)
                .min()
                .orElse(0);
        assertEquals(expected_high, actual_high);
        assertEquals(expected_avg, actual_avg);
        assertEquals(expected_low, actual_low);
    }

    @Test
    public void verifyDominationComparatorReverseOrderTest() {

        ArrayList<Integer> actual = new ArrayList<>();
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3,2,0,0));

        usedCarData.forEach(car -> car.dominationCount(usedCarData, car));
        var byYeardescending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        byYeardescending.forEach(c -> actual.add(c.getDominationCount()));
        assertEquals(actual, expected);

    }

}