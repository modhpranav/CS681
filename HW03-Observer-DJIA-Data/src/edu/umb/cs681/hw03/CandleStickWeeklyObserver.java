package edu.umb.cs681.hw03;

public class CandleStickWeeklyObserver implements Observer<WkSummary> {

    @Override
    public void update(Observable<WkSummary> obs, WkSummary data) {
        System.out.println("Weekly Candle Stick Observer");
        System.out.println("Open : "+data.getOpen());
        System.out.println("Close : "+data.getClose());
        System.out.println("High : "+data.getHigh());
        System.out.println("Low : "+data.getLow());
    }
}
