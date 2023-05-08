package edu.umb.cs681.hw15;

public class StockEvent {
    private String ticker;
    private double quote;

    public StockEvent(String tickers, double quote) {
        this.ticker = tickers;
        this.quote = quote;
    }
    //getters
    public String getTicker() {
        return ticker;
    }

    public double getQuote() {
        return quote;
    }
}
