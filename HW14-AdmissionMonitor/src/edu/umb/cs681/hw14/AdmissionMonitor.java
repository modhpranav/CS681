package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AdmissionMonitor {
    private int currentVisitors = 0;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Condition admissionCondition = lock.writeLock().newCondition();

    public AtomicBoolean done = new AtomicBoolean(false);
    boolean isMonitoring = true;

    public void setDone() {
        done.getAndSet(true);
    }
    public void enter() {
        lock.writeLock().lock();
        try {
            while (currentVisitors >= 3 && isMonitoring) {
                System.out.println("Too many visitors. Please wait for a while!");
                admissionCondition.await();
            }
            if (isMonitoring) {
                currentVisitors++;
            }
        }catch (Exception e) {
            System.out.println(e);
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
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int countCurrentVisitors() throws InterruptedException {
        lock.readLock().lock();
        try {
            return currentVisitors;
        }catch (Exception e) {
            System.out.println(e);
        }finally {
            lock.readLock().unlock();
        }
        return currentVisitors;
    }

    public void stopMonitoring() throws InterruptedException {
        lock.writeLock().lock();
        try {
            isMonitoring = false;
            admissionCondition.signalAll();
        }catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
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
    }

}
