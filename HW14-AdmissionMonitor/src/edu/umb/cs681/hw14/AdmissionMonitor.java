package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AdmissionMonitor {
    private int currentVisitors = 0;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Condition admissionCondition = lock.writeLock().newCondition();
    boolean isMonitoring = true;

    public void enter() throws InterruptedException {
        lock.writeLock().lock();
        try {
            while (currentVisitors >= 3 && isMonitoring) {
                System.out.println("Too many visitors. Please wait for a while!");
                admissionCondition.await();
            }
            if (isMonitoring) {
                currentVisitors++;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void exit() {
        lock.writeLock().lock();
        try {
            if (isMonitoring) {
                currentVisitors--;
                admissionCondition.signalAll();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int countCurrentVisitors() {
        lock.readLock().lock();
        try {
            return currentVisitors;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void stopMonitoring() {
        lock.writeLock().lock();
        try {
            isMonitoring = false;
            admissionCondition.signalAll();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AdmissionMonitor monitor = new AdmissionMonitor();
        Thread entranceThread = new Thread(new EntranceHandler(monitor));
        Thread exitThread = new Thread(new ExitHandler(monitor));
        Thread statsThread = new Thread(new StatsHandler(monitor));

        entranceThread.start();
        exitThread.start();
        statsThread.start();

        Thread.sleep(10000);

        monitor.stopMonitoring();

        entranceThread.join();
        exitThread.join();
        statsThread.join();
    }

}
