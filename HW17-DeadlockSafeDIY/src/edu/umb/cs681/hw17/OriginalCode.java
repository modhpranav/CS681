package edu.umb.cs681.hw17;

import java.util.concurrent.locks.ReentrantLock;

public class OriginalCode {
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
            lock2.lock();
            System.out.println("thread2 uses lock2");
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
            lock1.lock();
            System.out.println("thread2 uses lock1");
            lock1.unlock();
            lock2.unlock();
        });

        System.out.println("Started Running OriginalCode");

        thread1.start();
        thread2.start();

        try {
            thread1.join(5000);
            thread2.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Execution will not terminate automatically due to deadlock.");
        System.out.println("Execution timed out after 5 seconds due to deadlock.");
        System.exit(0);
    }
}
