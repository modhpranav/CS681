package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class RequestHandler implements Runnable {
    private static final Path[] FILES = {
            Paths.get("a.html"),
            Paths.get("b.html")
    };

    private final AccessCounter accessCounter;
    private final Random random;
    private volatile boolean running = true;

    public RequestHandler(AccessCounter accessCounter) {
        this.accessCounter = accessCounter;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (running) {
            Path file = FILES[random.nextInt(FILES.length)];
            accessCounter.increment(file);
            int count = accessCounter.getCount(file);
            System.out.printf("File %s has been accessed %d times.%n", file, count);
            try {
                Thread.sleep(random.nextInt(5000) + 1000);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void stop() {
        running = false;
    }

    public static void main(String[] args) throws InterruptedException {
        AccessCounter accessCounter = AccessCounter.getInstance();
        AtomicBoolean stopFlag = new AtomicBoolean(false);
        Thread[] threads = new Thread[15];
        RequestHandler[] handlers = new RequestHandler[15];

        for (int i = 0; i < threads.length; i++) {
            handlers[i] = new RequestHandler(accessCounter);
            threads[i] = new Thread(handlers[i]);
            threads[i].start();
        }

        Thread.sleep(10000);

        stopFlag.set(true);
        for (RequestHandler handler : handlers) {
            handler.stop();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        accessCounter.accessCountMap.forEach((path, value) -> {
            int count = value;
            System.out.println(path + " : " +  "Accessed " + count + " times.");
        });

    }
}
