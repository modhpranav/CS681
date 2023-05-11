package edu.umb.cs681.hw19;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable<T> extends Observable {
    private Map<String, Double> ticker_quote = new HashMap<>();
    private ReentrantLock lockTQ = new ReentrantLock();

    public void changeQuote(String tickers, double quote) {
        lockTQ.lock();
        try {
            ticker_quote.put(tickers, quote);
        } finally {
            lockTQ.unlock();
            this.notifyObservers(new StockEvent(tickers, quote));
        }
    }
}