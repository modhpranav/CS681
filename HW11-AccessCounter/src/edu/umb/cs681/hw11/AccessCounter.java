package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

    private static volatile AccessCounter instance;
    private static final Object instanceLock = new Object();
    final Map<Path, Integer> accessCountMap;
    private final ReentrantLock accessCountLock;

    private AccessCounter() {
        accessCountMap = new HashMap<>();
        accessCountLock = new ReentrantLock();
    }

    public static AccessCounter getInstance() {
        if (instance == null) {
            synchronized (instanceLock) {
                if (instance == null) {
                    instance = new AccessCounter();
                }
            }
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

