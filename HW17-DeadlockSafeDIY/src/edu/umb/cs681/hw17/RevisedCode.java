package edu.umb.cs681.hw17;

import java.util.concurrent.locks.ReentrantLock;

public class RevisedCode {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock1.lock();
            System.out.println("thread1 uses lock1");
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
            lock2.lock();
            System.out.println("thread1 uses lock2");
            lock2.unlock();
            lock1.unlock();
        });

        Thread thread2 = new Thread(() -> {
            lock1.lock();
            System.out.println("thread2 uses lock1");
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
            lock2.lock();
            System.out.println("thread2 uses lock2");
            lock2.unlock();
            lock1.unlock();
        });

        System.out.println("Started Running RevisedCode.");
        thread1.start();
        thread2.start();
    }
}
