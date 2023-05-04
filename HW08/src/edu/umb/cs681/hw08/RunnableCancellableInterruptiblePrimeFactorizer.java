package edu.umb.cs681.hw08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
    private boolean done = false;
    private boolean isInterrupted = false;
    private final ReentrantLock relock = new ReentrantLock();

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    @Override
    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {
            relock.lock();
            try {
                if (done) {
                    System.out.println("Thread #" + Thread.currentThread().getId() + " stopped generating prime factors.");
                    return;
                }
                if (Thread.interrupted()) {
                    System.out.println("Thread #" + Thread.currentThread().getId() + " was interrupted.");
                    isInterrupted = true;
                    return;
                }
                if (divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }
                if (dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                } else {
                    if (divisor == 2) {
                        divisor++;
                    } else {
                        divisor += 2;
                    }
                }
            } finally {
                relock.unlock();
            }
        }
    }

    @Override
    public void setDone() {
        relock.lock();
        try {
            done = true;
        } finally {
            relock.unlock();
        }
    }

    public boolean getIsInterrupted() {
        return isInterrupted;
    }

    public void interrupt() {
        Thread.currentThread().interrupt();
    }

    public List<Long> getFactors() {
        relock.lock();
        try {
            return new ArrayList<Long>(factors);
        } finally {
            relock.unlock();
        }
    }
}

