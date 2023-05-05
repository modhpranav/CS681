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
            Thread entranceThread = new Thread(new EntranceHandler(monitor));
            Thread exitThread = new Thread(new ExitHandler(monitor));
            Thread statsThread = new Thread(new StatsHandler(monitor));

            entranceThread.start();
            exitThread.start();
            statsThread.start();

            Thread.sleep(5000);

            monitor.stopMonitoring();

            entranceThread.join();
            exitThread.join();
            statsThread.join();

            assertFalse(entranceThread.isAlive());
            assertFalse(exitThread.isAlive());
            assertFalse(statsThread.isAlive());
        }
}
