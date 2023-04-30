package edu.umb.cs681.hw07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class FileSystemTest {

    @Test
    public void testGetFileSystem() throws InterruptedException {
        int numThreads = 100;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            executor.execute(() -> {
                FileSystem fs = FileSystem.getFileSystem();
                assertEquals(fs, FileSystem.getFileSystem());
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
