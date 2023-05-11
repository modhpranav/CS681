package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class AccessCounter {

    private static volatile AccessCounter instance;
    final ConcurrentHashMap<Path, Integer> accessCountMap;

    private AccessCounter() {
        accessCountMap = new ConcurrentHashMap<>();
    }

    public static AccessCounter getInstance() {
        if (instance == null) {
            if (instance == null) {
                instance = new AccessCounter();
            }
        }
        return instance;
    }


    public void increment(Path path) {
        accessCountMap.compute(path, (k, v) -> v == null ? 1 : v + 1);
    }

    public int getCount(Path path) {
        return accessCountMap.getOrDefault(path, 0);
    }
}
