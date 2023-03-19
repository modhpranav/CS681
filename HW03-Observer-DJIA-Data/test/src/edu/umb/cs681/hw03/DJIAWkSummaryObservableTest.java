package edu.umb.cs681.hw03;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DJIAWkSummaryObservableTest {

    public List<DSummary> LinesData(List<List<Double>> lines){
        List<DSummary> summaries = lines.stream()
                .map(row -> new DSummary(row.get(0), row.get(1), row.get(2), row.get(3)))
                .collect(Collectors.toList());
        return summaries;
    }
    @Test
    public void verifyDJIAWKSummaryTest() throws IOException {
        List<List<Double>> lines = TestFixtureInitializer.readCsv("HistoricalPrices.csv");
        List<DSummary> summaries = LinesData(lines);
        CandleStickWeeklyObserver observation = new CandleStickWeeklyObserver();
        DJIAWkSummaryObservable observable = new DJIAWkSummaryObservable();
        observable.addObserver(observation);
        summaries.stream().forEach((DSummary obj) -> {observable.addSummary(obj);});
        observable.removeObserver(observation);
    }
}
