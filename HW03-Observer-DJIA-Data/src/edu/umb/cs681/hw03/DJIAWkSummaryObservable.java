package edu.umb.cs681.hw03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DJIAWkSummaryObservable extends Observable<WkSummary> {
    private List<DSummary> dSummaryCollection;

    public DJIAWkSummaryObservable() {
        this.dSummaryCollection = new ArrayList<>();
    }

    public void addSummary(DSummary dSummary){
        this.dSummaryCollection.add(dSummary);

        if (this.dSummaryCollection.size() == 5) {
            double Open;
            double Close;
            double High;
            double Low;

            Open = this.dSummaryCollection.stream()
                    .findFirst()
                    .get()
                    .getOpen();

            Close = this.dSummaryCollection.stream()
                    .reduce((first, second) -> second)
                    .get()
                    .getClose();

            High = this.dSummaryCollection.stream()
                    .mapToDouble(DSummary::getHigh)
                    .max()
                    .orElse(Double.NaN);

            Low = this.dSummaryCollection.stream()
                    .mapToDouble(DSummary::getLow)
                    .min()
                    .orElse(Double.NaN);

            this.notifyObservers(new WkSummary(Open, High, Low, Close));
            this.dSummaryCollection.clear();
        }
    }

    public static void main(String[] args){}
}
