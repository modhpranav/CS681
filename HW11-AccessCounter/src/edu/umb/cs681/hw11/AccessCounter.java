package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

    private static final ReentrantLock singletonInstanceLock = new ReentrantLock();
    private static volatile AccessCounter instance;

    final Map<Path, Integer> accessCountMap;
    private final ReentrantLock accessCountLock;

    private AccessCounter() {
        accessCountMap = new HashMap<>();
        accessCountLock = new ReentrantLock();
    }

    public static AccessCounter getInstance() {
        singletonInstanceLock.lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
        } finally {
            singletonInstanceLock.unlock();
        }try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
        return instance;
    }

    public void increment(Path path) {
        accessCountLock.lock();
        try {
            Integer count = accessCountMap.get(path);
            if (count == null) {
                count = 0;
            }
            accessCountMap.put(path, count + 1);
        } finally {
            accessCountLock.unlock();
        }
    }

    public int getCount(Path path) {
        accessCountLock.lock();
        try {
            Integer count = accessCountMap.get(path);
            if (count == null) {
                count = 0;
            }
            return count;
        } finally {
            accessCountLock.unlock();
        }
    }
}
