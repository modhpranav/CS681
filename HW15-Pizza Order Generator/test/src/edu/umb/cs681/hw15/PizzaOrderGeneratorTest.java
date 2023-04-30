package edu.umb.cs681.hw15;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PizzaOrderGeneratorTest {

    OriginalPizzaOrderGenerator generatorOriginal;
    RevisedPizzaOrderGenerator generatorRevised;

    @BeforeEach
    void setUp() throws Exception {
        generatorOriginal = new OriginalPizzaOrderGenerator();
        generatorRevised = new RevisedPizzaOrderGenerator();
    }

    @Test
    void testOriginalPizzaOrderGenerator() {
        generatorOriginal.addTopping("Bell Pepper");
        generatorOriginal.addTopping("Mushrooms");
        generatorOriginal.addTopping("Onions");
        generatorOriginal.addTopping("Pineapple");
        String order = generatorOriginal.generateOrder();
        assertEquals("Bell Pepper Mushrooms Onions Pineapple ", order);
    }

    @Test
    void testRevisedPizzaOrderGenerator() throws InterruptedException {
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

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        String order = generatorRevised.generateOrder();
        assertTrue(order.contains("Bell Pepper"));
        assertTrue(order.contains("Mushrooms"));
        assertTrue(order.contains("Onions"));
        assertTrue(order.contains("Pineapple"));
    }
}

