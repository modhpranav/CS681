package edu.umb.cs681.hw16;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.*;

public class RevisedPizzaOrderGenerator {
    private List<String> toppings = new ArrayList<String>();
    private int numToppings = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void addTopping(String topping) {
        lock.lock();
        try {
            toppings.add(topping);
            numToppings++;
        } finally {
            lock.unlock();
        }
    }

    public String generateOrder() {
        String order = "";
        lock.lock();
        try {
            for (int i = 0; i < numToppings; i++) {
                order += toppings.get(i) + " ";
            }
        } finally {
            lock.unlock();
        }
        System.out.println("Toppings are: " + order);
        return order;
    }

    public static void main(String[] args) {

        RevisedPizzaOrderGenerator generatorRevised = new RevisedPizzaOrderGenerator();
        Thread thread1 = new Thread(() -> {
            generatorRevised.addTopping("Bell Pepper");
        });
        Thread thread2 = new Thread(() -> {
            generatorRevised.addTopping("Mushrooms");
        });
        Thread thread3 = new Thread(() -> {
            generatorRevised.addTopping("Onions");
        });
        Thread thread4 = new Thread(() -> {
            generatorRevised.addTopping("Pineapple");
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        generatorRevised.generateOrder();
    }

}
