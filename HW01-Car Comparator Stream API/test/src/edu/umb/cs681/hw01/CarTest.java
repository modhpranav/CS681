package edu.umb.cs681.hw01;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(53, 67, 72, 98));
        ArrayList<Integer> actual = new ArrayList<>();
        var byYearAscending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.toList());
        byYearAscending.forEach(c -> actual.add(c.getMileage()));
        assertEquals(actual, expected);
    }

    @Test
    public void verifyHighGroupMileageTest(){

        ArrayList<String> expected_high = new ArrayList<>();
        expected_high.add("Mercedes-4");
        expected_high.add("Maruti-3");

        var byHighGroupMileage = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.partitioningBy((Car car) -> car.getMileage() > 70,
                    Collectors.mapping(Car::getName, Collectors.toList())));
        assertEquals(expected_high, byHighGroupMileage.get(true));
    }

    @Test
    public void verifyHighGroupMileageAverageTest(){

        double expected_average = 85.0;

        var byHighGroupMileage = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.partitioningBy((Car car) -> car.getMileage() > 70));
        double actual_average = byHighGroupMileage.get(true).stream().mapToInt(Car::getMileage).average().orElse(Double.NaN);
        assertEquals(expected_average, actual_average);
    }

    @Test
    public void verifyHighGroupMileageHighestTest(){

        double expected_highest = 98.0;

        var byHighGroupMileage = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.partitioningBy((Car car) -> car.getMileage() > 70));
        double actual_highest = byHighGroupMileage.get(true).stream().mapToInt(Car::getMileage).max()
                .orElse(Integer.MAX_VALUE);
        assertEquals(expected_highest, actual_highest);
    }

    @Test
    public void verifyHighGroupMileageLowestTest(){

        double expected_lowest = 72.0;

        var byHighGroupMileage = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.partitioningBy((Car car) -> car.getMileage() > 70));
        double actual_lowest = byHighGroupMileage.get(true).stream().mapToInt(Car::getMileage).min()
                .orElse(Integer.MIN_VALUE);
        assertEquals(expected_lowest, actual_lowest);
    }

    @Test
    public void verifyHighGroupMileageCountTest(){

        long expected_count = 2;

        var byHighGroupMileage = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.partitioningBy((Car car) -> car.getMileage() > 70));
        long actual_count = byHighGroupMileage.get(true).stream().mapToInt(Car::getMileage).count();
        assertEquals(expected_count, actual_count);
    }

    @Test
    public void verifyLowGroupMileageTest(){

        ArrayList<String> expected_low = new ArrayList<>();
        expected_low.add("Hyundai-1");
        expected_low.add("Honda-2");

        var byLowGroupMileage = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.partitioningBy((Car car) -> car.getMileage() < 70,
                        Collectors.mapping(Car::getName, Collectors.toList())));
        assertEquals(expected_low, byLowGroupMileage.get(true));
    }

    @Test
    public void verifyLowGroupMileageAverageTest(){

        double expected_average = 60.0;

        var byLowGroupMileage = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.partitioningBy((Car car) -> car.getMileage() < 70));
        double actual_average = byLowGroupMileage.get(true).stream().mapToInt(Car::getMileage).average().orElse(Double.NaN);
        assertEquals(expected_average, actual_average);
    }

    @Test
    public void verifyLowGroupMileageHighestTest(){

        double expected_highest = 67.0;

        var byLowGroupMileage = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.partitioningBy((Car car) -> car.getMileage() < 70));
        double actual_highest = byLowGroupMileage.get(true).stream().mapToInt(Car::getMileage).max()
                .orElse(Integer.MAX_VALUE);
        assertEquals(expected_highest, actual_highest);
    }

    @Test
    public void verifyLowGroupMileageLowestTest(){

        double expected_lowest = 53.0;

        var byLowGroupMileage = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.partitioningBy((Car car) -> car.getMileage() < 70));
        double actual_lowest = byLowGroupMileage.get(true).stream().mapToInt(Car::getMileage).min()
                .orElse(Integer.MIN_VALUE);
        assertEquals(expected_lowest, actual_lowest);
    }

    @Test
    public void verifyLowGroupMileageCountTest(){

        long expected_count = 2;

        var bylowGroupMileage = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.partitioningBy((Car car) -> car.getMileage() < 70));
        long actual_count = bylowGroupMileage.get(true).stream().mapToInt(Car::getMileage).count();
        assertEquals(expected_count, actual_count);
    }

    @Test
    public void verifyMileageComparatorReverseOrderTest() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(98,72,67,53));
        ArrayList<Integer> actual = new ArrayList<>();
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
    public void verifyHighGroupPriceTest(){

        ArrayList<String> expected_high = new ArrayList<>();
        expected_high.add("Maruti-3");
        expected_high.add("Mercedes-4");

        var byHighGroupPrice = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.partitioningBy((Car car) -> car.getPrice() > 9500,
                        Collectors.mapping(Car::getName, Collectors.toList())));
        assertEquals(expected_high, byHighGroupPrice.get(true));
    }

    @Test
    public void verifyHighGroupPriceAverageTest(){

        double expected_average = 15000.0;

        var byHighGroupPrice = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.partitioningBy((Car car) -> car.getPrice() > 9500));
        double actual_average = byHighGroupPrice.get(true).stream().mapToInt(Car::getPrice).average().orElse(Double.NaN);
        assertEquals(expected_average, actual_average);
    }

    @Test
    public void verifyHighGroupPriceHighestTest(){

        double expected_highest = 20000.0;

        var byHighGroupPrice = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.partitioningBy((Car car) -> car.getPrice() > 9500));
        double actual_highest = byHighGroupPrice.get(true).stream().mapToInt(Car::getPrice).max()
                .orElse(Integer.MAX_VALUE);
        assertEquals(expected_highest, actual_highest);
    }

    @Test
    public void verifyHighGroupPriceLowestTest(){

        double expected_lowest = 10000.0;

        var byHighGroupPrice = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.partitioningBy((Car car) -> car.getPrice() > 9500));
        double actual_lowest = byHighGroupPrice.get(true).stream().mapToInt(Car::getPrice).min()
                .orElse(Integer.MIN_VALUE);
        assertEquals(expected_lowest, actual_lowest);
    }

    @Test
    public void verifyHighGroupPriceCountTest(){

        long expected_count = 2;

        var byHighGroupprice = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.partitioningBy((Car car) -> car.getPrice() > 9500));
        long actual_count = byHighGroupprice.get(true).stream().mapToInt(Car::getPrice).count();
        assertEquals(expected_count, actual_count);
    }

    @Test
    public void verifyLowGroupPriceTest(){

        ArrayList<String> expected_low = new ArrayList<>();
        expected_low.add("Hyundai-1");
        expected_low.add("Honda-2");

        var byLowGroupPrice = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.partitioningBy((Car car) -> car.getPrice() < 9500,
                        Collectors.mapping(Car::getName, Collectors.toList())));
        assertEquals(expected_low, byLowGroupPrice.get(true));
    }

    @Test
    public void verifyLowGroupPriceAverageTest(){

        double expected_average = 8500.0;

        var byLowGroupPrice = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.partitioningBy((Car car) -> car.getPrice() < 9500));
        double actual_average = byLowGroupPrice.get(true).stream().mapToInt(Car::getPrice).average().orElse(Double.NaN);
        assertEquals(expected_average, actual_average);
    }

    @Test
    public void verifyLowGroupPriceHighestTest(){

        double expected_highest = 9000.0;

        var byLowGroupPrice = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.partitioningBy((Car car) -> car.getPrice() < 9500));
        double actual_highest = byLowGroupPrice.get(true).stream().mapToInt(Car::getPrice).max()
                .orElse(Integer.MAX_VALUE);
        assertEquals(expected_highest, actual_highest);
    }

    @Test
    public void verifyLowGroupPriceLowestTest(){

        double expected_lowest = 8000.0;

        var byLowGroupPrice = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.partitioningBy((Car car) -> car.getPrice() < 9500));
        double actual_lowest = byLowGroupPrice.get(true).stream().mapToInt(Car::getPrice).min()
                .orElse(Integer.MIN_VALUE);
        assertEquals(expected_lowest, actual_lowest);
    }

    @Test
    public void verifyLowGroupPriceCountTest(){

        long expected_count = 2;

        var bylowGroupPrice = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.partitioningBy((Car car) -> car.getPrice() < 9500));
        long actual_count = bylowGroupPrice.get(true).stream().mapToInt(Car::getPrice).count();
        assertEquals(expected_count, actual_count);
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
    public void verifyHighGroupYearTest(){

        ArrayList<String> expected_high = new ArrayList<>();
        expected_high.add("Honda-2");
        expected_high.add("Maruti-3");
        expected_high.add("Mercedes-4");

        var byHighGroupYear = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.partitioningBy((Car car) -> car.getYear() >= 2001,
                        Collectors.mapping(Car::getName, Collectors.toList())));
        assertEquals(expected_high, byHighGroupYear.get(true));
    }

    @Test
    public void verifyHighGroupYearAverageTest(){

        double expected_average = 2002.3333333333333;

        var byHighGroupYear = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.partitioningBy((Car car) -> car.getYear() >= 2001));
        double actual_average = byHighGroupYear.get(true).stream().mapToInt(Car::getYear).average().orElse(Double.NaN);
        assertEquals(expected_average, actual_average);
    }

    @Test
    public void verifyHighGroupYearHighestTest(){

        double expected_highest = 2004.0;

        var byHighGroupYear = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.partitioningBy((Car car) -> car.getYear() >= 2001));
        double actual_highest = byHighGroupYear.get(true).stream().mapToInt(Car::getYear).max()
                .orElse(Integer.MAX_VALUE);
        assertEquals(expected_highest, actual_highest);
    }

    @Test
    public void verifyHighGroupYearLowestTest(){

        double expected_lowest = 2001.0;

        var byHighGroupYear = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.partitioningBy((Car car) -> car.getYear() >= 2001));
        double actual_lowest = byHighGroupYear.get(true).stream().mapToInt(Car::getYear).min()
                .orElse(Integer.MIN_VALUE);
        assertEquals(expected_lowest, actual_lowest);
    }

    @Test
    public void verifyHighGroupYearCountTest(){

        long expected_count = 3;

        var byHighGroupYear = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.partitioningBy((Car car) -> car.getYear() >= 2001));
        long actual_count = byHighGroupYear.get(true).stream().mapToInt(Car::getYear).count();
        assertEquals(expected_count, actual_count);
    }

    @Test
    public void verifyLowGroupYearTest(){

        ArrayList<String> expected_low = new ArrayList<>();
        expected_low.add("Hyundai-1");
        expected_low.add("Honda-2");

        var byLowGroupYear = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.partitioningBy((Car car) -> car.getYear() <= 2001,
                        Collectors.mapping(Car::getName, Collectors.toList())));
        assertEquals(expected_low, byLowGroupYear.get(true));
    }

    @Test
    public void verifyLowGroupYearAverageTest(){

        double expected_average = 2000.5;

        var byLowGroupYear = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.partitioningBy((Car car) -> car.getYear() <= 2001));
        double actual_average = byLowGroupYear.get(true).stream().mapToInt(Car::getYear).average().orElse(Double.NaN);
        assertEquals(expected_average, actual_average);
    }

    @Test
    public void verifyLowGroupYearHighestTest(){

        double expected_highest = 2001.0;

        var byLowGroupYear = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.partitioningBy((Car car) -> car.getYear() <= 2001));
        double actual_highest = byLowGroupYear.get(true).stream().mapToInt(Car::getYear).max()
                .orElse(Integer.MAX_VALUE);
        assertEquals(expected_highest, actual_highest);
    }

    @Test
    public void verifyLowGroupYearLowestTest(){

        double expected_lowest = 2000.0;

        var byLowGroupYear = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.partitioningBy((Car car) -> car.getYear() <= 2001));
        double actual_lowest = byLowGroupYear.get(true).stream().mapToInt(Car::getYear).min()
                .orElse(Integer.MIN_VALUE);
        assertEquals(expected_lowest, actual_lowest);
    }

    @Test
    public void verifyLowGroupYearCountTest(){

        long expected_count = 2;

        var bylowGroupYear = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .collect(Collectors.partitioningBy((Car car) -> car.getYear() <= 2001));
        long actual_count = bylowGroupYear.get(true).stream().mapToInt(Car::getYear).count();
        assertEquals(expected_count, actual_count);
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
    public void verifyDominationComparatorNaturalOrderTest() {

        ArrayList<Integer> actual = new ArrayList<>();
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var byDominationAscending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.toList());
        byDominationAscending.forEach(c -> actual.add(c.getDominationCount()));
        assertEquals(actual, expected);

    }

    @Test
    public void verifyHighGroupDominationTest(){

        ArrayList<String> expected_high = new ArrayList<>();
        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var byHighGroupDomination = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.partitioningBy((Car car) -> car.getDominationCount() > 1,
                        Collectors.mapping(Car::getName, Collectors.toList())));
        assertEquals(expected_high, byHighGroupDomination.get(true));
    }

    @Test
    public void verifyHighGroupDominationHighestTest(){

        double expected_highest = 2.147483647E9;
        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var byHighGroupDomination = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.partitioningBy((Car car) -> car.getDominationCount() > 1));
        double actual_highest = byHighGroupDomination.get(true).stream().mapToInt(Car::getDominationCount).max()
                .orElse(Integer.MAX_VALUE);
        assertEquals(expected_highest, actual_highest);
    }

    @Test
    public void verifyHighGroupDominationLowestTest(){

        double expected_lowest = -2.147483648E9;
        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var byHighGroupDomination = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.partitioningBy((Car car) -> car.getDominationCount() > 1));
        double actual_lowest = byHighGroupDomination.get(true).stream().mapToInt(Car::getDominationCount).min()
                .orElse(Integer.MIN_VALUE);
        assertEquals(expected_lowest, actual_lowest);
    }

    @Test
    public void verifyHighGroupDominationCountTest(){

        long expected_count = 0;
        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var byHighGroupDomination = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.partitioningBy((Car car) -> car.getDominationCount() > 1));
        long actual_count = byHighGroupDomination.get(true).stream().mapToInt(Car::getDominationCount).count();
        assertEquals(expected_count, actual_count);
    }

    @Test
    public void verifyLowGroupDominationTest(){

        ArrayList<String> expected_low = new ArrayList<>();
        expected_low.add("Hyundai-1");
        expected_low.add("Honda-2");
        expected_low.add("Maruti-3");
        expected_low.add("Mercedes-4");
        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var byLowGroupDomination = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.partitioningBy((Car car) -> car.getDominationCount() < 1,
                        Collectors.mapping(Car::getName, Collectors.toList())));
        assertEquals(expected_low, byLowGroupDomination.get(true));
    }

    @Test
    public void verifyLowGroupDominationAverageTest(){

        double expected_average = 0.0;
        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var byLowGroupDomination = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.partitioningBy((Car car) -> car.getDominationCount() < 1));
        double actual_average = byLowGroupDomination.get(true).stream().mapToInt(Car::getDominationCount).average().orElse(Double.NaN);
        assertEquals(expected_average, actual_average);
    }

    @Test
    public void verifyLowGroupDominationHighestTest(){

        double expected_highest = 0.0;
        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var byLowGroupDomination = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.partitioningBy((Car car) -> car.getDominationCount() < 1));
        double actual_highest = byLowGroupDomination.get(true).stream().mapToInt(Car::getDominationCount).max()
                .orElse(Integer.MAX_VALUE);
        assertEquals(expected_highest, actual_highest);
    }

    @Test
    public void verifyLowGroupDominationLowestTest(){

        double expected_lowest = 0.0;
        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var byLowGroupDomination = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.partitioningBy((Car car) -> car.getDominationCount() < 1));
        double actual_lowest = byLowGroupDomination.get(true).stream().mapToInt(Car::getDominationCount).min()
                .orElse(Integer.MIN_VALUE);
        assertEquals(expected_lowest, actual_lowest);
    }

    @Test
    public void verifyLowGroupDominationCountTest(){

        long expected_count = 4;

        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var bylowGroupDomination = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.partitioningBy((Car car) -> car.getDominationCount() < 1));
        long actual_count = bylowGroupDomination.get(true).stream().mapToInt(Car::getDominationCount).count();
        assertEquals(expected_count, actual_count);
    }

    @Test
    public void verifyDominationComparatorReverseOrderTest() {

        ArrayList<Integer> actual = new ArrayList<>();
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0,0,0,0));

        usedCarData.forEach(car -> car.setDominationCount(usedCarData));
        var byDominationdescending = usedCarData.stream()
                .sorted(Comparator.comparing(Car::getDominationCount, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        byDominationdescending.forEach(c -> actual.add(c.getDominationCount()));
        assertEquals(actual, expected);

    }

}