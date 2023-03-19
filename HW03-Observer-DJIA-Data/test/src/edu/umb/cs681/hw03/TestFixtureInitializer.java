package edu.umb.cs681.hw03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestFixtureInitializer {
    public static List<List<Double>> readCsv(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try (Stream<String> lines = Files.lines(path)) {
            List<List<Double>> csv = lines.skip(1).map(line -> {
                List<String> values = Arrays.asList(line.split(","));
                List<Double> row = values.stream()
                        .skip(1)
                        .map(Double::parseDouble)
                        .collect(Collectors.toList());
                return row;
            }).collect(Collectors.toList());
            return csv;
        }
    }
}

