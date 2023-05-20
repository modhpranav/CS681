package edu.umb.cs681.hw17;

import java.util.concurrent.locks.ReentrantLock;

public class RevisedCode {
    private static ReentrantLock pepperoniLock = new ReentrantLock();
    private static ReentrantLock cheeseLock = new ReentrantLock();

    public static void addPepperoni() {
        pepperoniLock.lock();
        System.out.println("Lock acquired for adding pepperoni");

        try {
            Thread.sleep(1000);

            cheeseLock.lock();
            System.out.println("Lock acquired for adding cheese");

            System.out.println("Pepperoni added to the pizza.");
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
        pepperoniLock.lock();
        System.out.println("Lock acquired for adding pepperoni");

        try {
            Thread.sleep(1000);

            cheeseLock.lock();
            System.out.println("Lock acquired for adding cheese");

            System.out.println("Cheese added to the pizza.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cheeseLock.unlock();
            System.out.println("Lock released for cheese");

            pepperoniLock.unlock();
            System.out.println("Lock released for pepperoni");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> addPepperoni());
        Thread thread2 = new Thread(() -> addCheese());

        thread1.start();
        thread2.start();
    }
}
