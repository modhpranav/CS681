package edu.umb.cs681.hw14;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AdmissionMonitorTest {
        private AdmissionMonitor monitor;

        @BeforeEach
        void setUp() {
            monitor = new AdmissionMonitor();
        }

        @Test
        void testExit() throws InterruptedException {
            monitor.enter();
            monitor.exit();
            assertEquals(0, monitor.countCurrentVisitors());
        }

        @Test
        void testCountCurrentVisitors() throws InterruptedException {
            monitor.enter();
            assertEquals(1, monitor.countCurrentVisitors());
            monitor.enter();
            assertEquals(2, monitor.countCurrentVisitors());
            monitor.exit();
            assertEquals(1, monitor.countCurrentVisitors());
        }

        @Test
        void testStopMonitoring() throws InterruptedException {
            AdmissionMonitor monitor = new AdmissionMonitor();
            EntranceHandler entranceHandler = new EntranceHandler(monitor);
            Thread entranceThread = new Thread(entranceHandler);
            ExitHandler exitHandler = new ExitHandler(monitor);
            Thread exitThread = new Thread(exitHandler);
            StatsHandler statsHandler = new StatsHandler(monitor);
            Thread statsThread = new Thread(statsHandler);

            entranceThread.start();
            exitThread.start();
            statsThread.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            entranceHandler.setDone();
            exitHandler.setDone();
            statsHandler.setDone();
            monitor.setDone();

            entranceThread.interrupt();
            exitThread.interrupt();
            statsThread.interrupt();

            try {
                entranceThread.join();
                exitThread.join();
                statsThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            assertFalse(entranceThread.isAlive());
            assertFalse(exitThread.isAlive());
            assertFalse(statsThread.isAlive());
        }
}
