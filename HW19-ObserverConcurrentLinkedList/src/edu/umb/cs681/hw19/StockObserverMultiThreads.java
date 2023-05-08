package edu.umb.cs681.hw19;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockObserverMultiThreads {

    public static void main(String[] args) throws InterruptedException {
        StockQuoteObservable stock = new StockQuoteObservable();
        String code = "ABC";
        Double value = 300.0;

        Random randomValue = new Random();

        stock.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            double quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer1 -> StockEvent: " + ticker + "@" + quote);
        });

        stock.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            double quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer2 -> StockEvent: " + ticker + "@" + quote);
        });

        stock.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            double quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer3 -> StockEvent: " + ticker + "@" + quote);
        });

        System.out.println("Total Observers are: " + stock.countObservers());

        System.out.println("New Stock Addition: " + code);
        stock.setQuote(code, value);

        value = 50.0;
        System.out.println("StockQuote 'ABC' updated");
        stock.changeQuote(code, value);

        int numThreads = 15;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Thread t =
                    new Thread(() ->{ stock.setQuote("ABC", randomValue.nextDouble()*100 + 500);
                        stock.notifyObservers(new StockEvent("ABC", randomValue.nextDouble()*100 + 500));});
            threads.add(t);
        }

        for (Thread t : threads) {
            t.start();
            t.interrupt();
        }

        for (Thread t : threads) {
            t.join();
        }

    }
}
