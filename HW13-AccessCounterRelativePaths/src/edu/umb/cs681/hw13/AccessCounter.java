package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

    private static volatile AccessCounter instance;

    private static final ReentrantLock singletonInstanceLock = new ReentrantLock();
    private final Map<Path, Integer> accessCountMap;
    private final ReentrantReadWriteLock rwLock;


    private AccessCounter() {
        accessCountMap = new HashMap<>();
        rwLock = new ReentrantReadWriteLock();
    }

    public static AccessCounter getInstance() {
        if (instance == null) {
            singletonInstanceLock.lock();
            try {
                if (instance == null) {
                    instance = new AccessCounter();
                }
            } finally {
                singletonInstanceLock.unlock();
            }try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                System.out.println(e.toString());
            }
        }
        return instance;
    }


    public void increment(Path path) {
        rwLock.writeLock().lock();
        try {
            Integer count = accessCountMap.get(path);
            if (count == null) {
                count = 0;
            }
            accessCountMap.put(path, count + 1);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public int getCount(Path path) {
        rwLock.readLock().lock();
        try {
            Integer count = accessCountMap.get(path);
            if (count == null) {
                count = 0;
            }
            return count;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public Map<Path, Integer> getAccessCountMap() {
        return accessCountMap;
    }
}
