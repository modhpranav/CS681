package edu.umb.cs681.hw05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BostonHousing {
    public static void main(String[] args) {
        String fileName = "bos-housing.csv";
        List<String[]> data = parseCsvFile(fileName);

//        Data Processing #1
        dataProcessing1(data);

//        Data Processing #2
        dataProcessing2(data);

//        Data Processing #3
        dataProcessing3(data);

//        Data Processing #4
        dataProcessing4(data);

//        Data Processing #4
        dataProcessing5(data);

    }

    public static List<String[]> parseCsvFile(String fileName) {
        List<String[]> data;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            data = br.lines()
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + e);
        }
        return data;
    }

    public static void dataProcessing1(List<String []> data){
        List<String[]> rowsNearToChas = findRowsNearToChas(data);
        System.out.println("Highest price of houses near chas: " + highestPrice(rowsNearToChas));
        System.out.println("Lowest price of houses near chas: " + lowestPrice(rowsNearToChas));
        System.out.println("Average price of houses near chas: " + averagePrice(rowsNearToChas));
    }
    public static List<String[]> findRowsNearToChas(List<String[]> data) {
        List<String[]> housesNearChas = data.stream()
                .filter(house -> house[3].replaceAll("\"", "").equals("1"))
                .collect(Collectors.toList());
        return housesNearChas;
    }

    public static double highestPrice(List<String[]> data){

        return data.stream()
                .mapToDouble(house -> Double.parseDouble(house[13]))
                .max()
                .orElse(Double.NaN);
    }

    public static double lowestPrice(List<String[]> data){

        return data.stream()
                .mapToDouble(house -> Double.parseDouble(house[13]))
                .min()
                .orElse(Double.NaN);
    }

    public static double averagePrice(List<String[]> data){

        return data.stream()
                .mapToDouble(house -> Double.parseDouble(house[13]))
                .average()
                .orElse(Double.NaN);
    }

    public static void dataProcessing2(List<String[]> data){

        int totalRows = data.size();

        int top10percent = (int) Math.ceil(totalRows * 0.1);

        List<String> areasLowCrimeRate = data.stream()
                .skip(1)
                .sorted(Comparator.comparingDouble(house -> Float.parseFloat(house[0])))
                .limit(top10percent)
                .map(house -> house[2])
                .toList();

        List<String> areasLowPTRatio = data.stream()
                .skip(1)
                .sorted(Comparator.comparingDouble(house -> Double.parseDouble(house[10])))
                .limit(top10percent)
                .map(house -> house[2])
                .toList();

        double[] maxPriceNoxRooms = new double[]{data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[3].replaceAll("\"", "")))
                .summaryStatistics()
                .getMax()};

        double[] minPriceNoxRooms = new double[]{data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[3].replaceAll("\"", "")))
                .summaryStatistics()
                .getMin()};

        double[] avgPriceNoxRooms = new double[]{data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[3].replaceAll("\"", "")))
                .summaryStatistics()
                .getAverage()};

        double[] maxNox = new double[]{data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[4]))
                .summaryStatistics()
                .getMax()};

        double[] minNox = new double[]{data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[4]))
                .summaryStatistics()
                .getMin()};

        double[] avgNox = new double[]{data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[4]))
                .summaryStatistics()
                .getAverage()};

        double[] maxRooms = new double[]{data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[5]))
                .summaryStatistics()
                .getMax()};

        double[] minRooms = new double[]{data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[5]))
                .summaryStatistics()
                .getMin()};

        double[] avgRooms = new double[]{data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[5]))
                .summaryStatistics()
                .getAverage()};

        System.out.println("Areas/blocks with lowest crime rate: " + areasLowCrimeRate);
        System.out.println("Areas/blocks with lowest pupil-teacher ratio: " + areasLowPTRatio);

        System.out.println("Max price, NOX concentration, and # of rooms: " + Arrays.toString(maxPriceNoxRooms));
        System.out.println("Min price, NOX concentration, and # of rooms: " + Arrays.toString(minPriceNoxRooms));
        System.out.println("Average price, NOX concentration, and # of rooms: " + Arrays.toString(avgPriceNoxRooms));

    }

    public static void dataProcessing3(List<String[]> data){

        OptionalDouble maxMedianVal = data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[13]))
                .sorted()
                .skip(data.size() / 2)
                .findFirst();
        OptionalDouble minMedianVal = data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[13]))
                .sorted()
                .limit(data.size() / 2)
                .max();

        System.out.println("Highest median value of owner-occupied homes: " + maxMedianVal.getAsDouble());
        System.out.println("Lowest median value of owner-occupied homes: " + minMedianVal.getAsDouble());

        double avgDistance = data.stream()
                .skip(1)
                .mapToDouble(house -> Double.parseDouble(house[7]))
                .average()
                .getAsDouble();

        System.out.println("Average distance to five Boston employment centres: " + avgDistance);
    }

    public static void dataProcessing4(List<String[]> data) {

        double[] indus = data.stream()
                .skip(1)
                .filter(s -> Double.parseDouble(s[0]) > 0.5)
                .mapToDouble(s -> Double.parseDouble(s[2]))
                .toArray();

        double indusSum = Arrays.stream(indus).sum();
        double indusMean = indusSum / indus.length;

        double indusSumOfSquares = Arrays.stream(indus)
                .map(d -> d - indusMean)
                .map(d -> d * d)
                .sum();

        double indusStdDev = Math.sqrt(indusSumOfSquares / (indus.length - 1));

        System.out.println("Average of INDUS for CRIM > 0.5: " + indusMean);
        System.out.println("Standard deviation of INDUS for CRIM > 0.5: " + indusStdDev);
    }

    public static void dataProcessing5(List<String[]> data){

        String[] townWithHighestBlacks = data.stream()
                .skip(1)
                .max(Comparator.comparingDouble(row -> Double.parseDouble(row[12])))
                .orElseThrow(() -> new RuntimeException("No town found"));

        double[] distances = Arrays.stream(townWithHighestBlacks)
                .skip(3)
                .limit(5)
                .skip(1)
                .mapToDouble(Double::parseDouble)
                .toArray();
        double[] weights = { 1, 2, 3, 4, 5 };
        double weightedSum = 0;
        double weightSum = Arrays.stream(weights).sum();
        for (int i = 0; i < distances.length; i++) {
            weightedSum += distances[i] * weights[i];
        }
        double weightedMedian = 0;
        double cumulativeWeight = 0;
        for (int i = 0; i < distances.length; i++) {
            cumulativeWeight += weights[i];
            if (cumulativeWeight >= weightSum / 2) {
                weightedMedian = distances[i];
                break;
            }
        }

        System.out.println("Town with highest proportion of blacks: " + townWithHighestBlacks[0]);
        System.out.println("Weighted median distance to Boston employment centres: " + weightedMedian);
    }
}
