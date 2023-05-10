package edu.umb.cs681.hw07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FileSystemTest {

    @Test
    public void testGetFileSystem() throws InterruptedException {
        int numThreads = 100;
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            Thread t = new Thread(() -> {
                FileSystem fs = FileSystem.getFileSystem();
                assertEquals(fs, FileSystem.getFileSystem());
            });
            threads.add(t);
        }

        for (Thread t : threads) {
            t.start();
        }
    }
}
