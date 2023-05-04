package edu.umb.cs681.hw09;

public class RunnableClass implements Runnable {
    private final Aircraft aircraft;

    public RunnableClass(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @Override
    public void run() {
        aircraft.setPosition(10.00, -21.60, 4000);

        Thread[] threads = new Thread[15];
        for (int i = 0; i < threads.length; i++) {
            int latitude = 30 + i;
            threads[i] = new Thread(() -> {
                for (int j = 1; j < 4; j++) {
                    aircraft.setPosition(latitude, -13, j * 100);
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("New position: " + aircraft.getPosition().coordinate());
    }

    public static void main(String[] args){
        Aircraft aircraft = new Aircraft(new Position(42.3601, -71.0589, 100));
        RunnableClass runnable = new RunnableClass(aircraft);

        runnable.run();
    }

}

