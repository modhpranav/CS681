package edu.umb.cs681.hw08;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

public class RunnableCancellableInterruptiblePrimeFactorizerTest {

    @Test
    public void testGeneratePrimeFactors() throws InterruptedException {
        RunnableCancellableInterruptiblePrimeFactorizer factorizer =
                new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, 6);

        Thread thread = new Thread(factorizer);
        thread.start();

        Thread.sleep(1000);

        factorizer.setDone();
        thread.interrupt();

        thread.join();

        List<Long> factors = factorizer.getFactors();

        assertEquals(List.of(2L, 2L, 3L, 3L), factors);
    }
}
