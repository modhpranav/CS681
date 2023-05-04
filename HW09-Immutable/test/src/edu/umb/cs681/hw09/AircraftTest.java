package edu.umb.cs681.hw09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AircraftTest {

    @Test
    public void testSetPosition() throws InterruptedException {
        Position position = new Position(20.00, -10.60, 40000);
        Aircraft aircraft = new Aircraft(position);

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
            thread.join();
        }

        Position finalPosition = aircraft.getPosition();
        assertEquals(44.0, finalPosition.latitude());
        assertEquals(-13, finalPosition.longitude());
        assertEquals(300, finalPosition.altitude());
    }
}
