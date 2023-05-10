package edu.umb.cs681.hw06.primes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RunnableCancellablePrimeGeneratorTest {

    private RunnableCancellablePrimeGenerator primeGenerator;

    @BeforeEach
    public void setUp() {
        primeGenerator = new RunnableCancellablePrimeGenerator(1, 100);
    }

    @Test
    public void testGeneratePrimes() throws InterruptedException {
        Thread t = new Thread(primeGenerator);
        t.start();
        Thread.sleep(100);
        primeGenerator.setDone();

        List<Long> expectedPrimes = new LinkedList<>();
        expectedPrimes.add(2L);
        expectedPrimes.add(3L);
        expectedPrimes.add(5L);
        expectedPrimes.add(7L);
        expectedPrimes.add(11L);
        expectedPrimes.add(13L);
        expectedPrimes.add(17L);
        expectedPrimes.add(19L);
        expectedPrimes.add(23L);
        expectedPrimes.add(29L);
        expectedPrimes.add(31L);
        expectedPrimes.add(37L);
        expectedPrimes.add(41L);
        expectedPrimes.add(43L);
        expectedPrimes.add(47L);
        expectedPrimes.add(53L);
        expectedPrimes.add(59L);
        expectedPrimes.add(61L);
        expectedPrimes.add(67L);
        expectedPrimes.add(71L);
        expectedPrimes.add(73L);
        expectedPrimes.add(79L);
        expectedPrimes.add(83L);
        expectedPrimes.add(89L);
        expectedPrimes.add(97L);

        assertEquals(expectedPrimes, primeGenerator.getPrimes(), "Generated primes are not as expected");
    }
}
