package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class AccessCounter {

    final ConcurrentHashMap<Path, Integer> accessCountMap;

    private AccessCounter() {
        accessCountMap = new ConcurrentHashMap<>();
    }

    public static AccessCounter getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final AccessCounter instance = new AccessCounter();
    }

    public void increment(Path path) {
        accessCountMap.compute(path, (k, v) -> v == null ? 1 : v + 1);
    }

    public int getCount(Path path) {
        return accessCountMap.getOrDefault(path, 0);
    }
}
