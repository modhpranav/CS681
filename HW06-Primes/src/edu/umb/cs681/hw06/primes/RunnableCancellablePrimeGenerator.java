package edu.umb.cs681.hw06.primes;

import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {

    private final ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = false;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimes(){
        lock.lock();
        try {
                for (long n = from; n <= to; n++) {
                    if (done) {
                        System.out.println("Stopped generating prime numbers.");
                        this.primes.clear();
                        break;
                    }
                    if (isPrime(n)) {
                        this.primes.add(n);
                    }
                }
        }finally{
                lock.unlock();
            }
    }

    public static void main(String[] args) {
        RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100);
        Thread thread = new Thread(gen);
        thread.start();
        gen.setDone();
        gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
    }
}

