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
            }try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                System.out.println(e.toString());
                return;
            }
        }
    }

    public boolean getIsInterrupted() {
        return isInterrupted;
    }

    public void interrupt(){
        isInterrupted = false;
    }

    public List<Long> getFactors() {
        relock.lock();
        try {
            return new ArrayList<Long>(factors);
        } finally {
            relock.unlock();
        }
    }

    public static void main(String[] args) {
        RunnableCancellableInterruptiblePrimeFactorizer gen = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, 6);
        Thread thread = new Thread(gen);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.setDone();
        thread.interrupt();
        gen.getPrimeFactors().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + gen.getFactors().size() + " prime numbers are found.");
    }
}

