package edu.umb.cs681.hw15;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockObserverMultiThreads {

    public static void main(String[] args) throws InterruptedException {
        StockQuoteObservable stock = new StockQuoteObservable();

        Random random = new Random();

        stock.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).tickers();
            double quote = ((StockEvent) obj).quote();
            System.out.println("Observer1 -> StockEvent: " + ticker + "@" + quote);
        });

        stock.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).tickers();
            double quote = ((StockEvent) obj).quote();
            System.out.println("Observer2 -> StockEvent: " + ticker + "@" + quote);
        });

        stock.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).tickers();
            double quote = ((StockEvent) obj).quote();
            System.out.println("Observer3 -> StockEvent: " + ticker + "@" + quote);
        });

        System.out.println("Total Observers are: " + stock.countObservers());

        String code = "ABC";
        double value = 300.0;
        System.out.println("New Stock Addition: " + code);
        stock.changeQuote(code, value);

        value = 50.0;
        System.out.println("StockQuote 'ABC' updated");
        stock.changeQuote(code, value);

        int numThreads = 15;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            String ticker = "ABC" + (i + 1);
            double quote = random.nextDouble() * 100 + 500;
            Thread t = new Thread(() -> {
                stock.changeQuote(ticker, quote);
                stock.notifyObservers(new StockEvent(ticker, quote));
            });
            threads.add(t);
        }

        for (Thread t : threads) {
            t.start();
        }

    }
}
