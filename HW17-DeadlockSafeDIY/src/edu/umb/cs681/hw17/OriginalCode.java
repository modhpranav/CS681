package edu.umb.cs681.hw17;

import java.util.concurrent.locks.ReentrantLock;

public class OriginalCode {
    private static ReentrantLock pepperoniLock = new ReentrantLock();
    private static ReentrantLock cheeseLock = new ReentrantLock();

    public static void addPepperoni() {
        pepperoniLock.lock();
        System.out.println("Lock acquired for adding pepperoni");

        try {
            Thread.sleep(1000);

            cheeseLock.lock();
            System.out.println("Lock acquired for adding cheese");

            System.out.println("Topping Pepperoni added to the pizza.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cheeseLock.unlock();
            System.out.println("Lock released for cheese");

            pepperoniLock.unlock();
            System.out.println("Lock released for pepperoni");
        }
    }

    public static void addCheese() {
        cheeseLock.lock();
        System.out.println("Lock acquired for cheese");

        try {
            Thread.sleep(1000);

            pepperoniLock.lock();
            System.out.println("Lock acquired for adding pepperoni");

            System.out.println("Cheese added to the pizza.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pepperoniLock.unlock();
            System.out.println("Lock released for pepperoni");

            cheeseLock.unlock();
            System.out.println("Lock released for cheese");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> addPepperoni());
        Thread thread2 = new Thread(() -> addCheese());

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
