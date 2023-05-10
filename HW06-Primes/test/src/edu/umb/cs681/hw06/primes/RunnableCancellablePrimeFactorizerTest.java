package edu.umb.cs681.hw06.primes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RunnableCancellablePrimeFactorizerTest {

    private RunnableCancellablePrimeFactorizer factorizer;

    @BeforeEach
    void setUp() throws Exception {
        factorizer = new RunnableCancellablePrimeFactorizer(36, 2, 7);
    }

    @Test
    void testGeneratePrimeFactors() {
        Thread thread = new Thread(factorizer);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        factorizer.setDone();

        List<Long> expected = new ArrayList<>(List.of(2L, 2L, 3L, 3L));
        List<Long> actual = factorizer.getPrimeFactors();
        assertEquals(expected, actual);
    }

    @Test
    public void testFactorizerPrimesNoSleep(){
        Thread thread = new Thread(factorizer);
        thread.start();

        factorizer.setDone();

        List<Long> expected = new ArrayList<>(List.of(2L, 2L, 3L, 3L));
        assertEquals(expected, factorizer.getPrimeFactors());
    }

}

