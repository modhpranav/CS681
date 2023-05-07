package edu.umb.cs681.hw10;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class ThreadSyncTest {

    @Test
    public void ThreadTest() throws InterruptedException {

        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            threads.add(new Thread(() -> {

                Directory root = new Directory(null, "root", 0, LocalDateTime.now());
                Directory apps = new Directory(root, "Apps", 0, LocalDateTime.now());
                Directory bin = new Directory(root, "bin", 0, LocalDateTime.now());
                Directory home = new Directory(root, "home", 0, LocalDateTime.now());
                File x = new File(apps, "x", 50, LocalDateTime.now());
                File y = new File(bin, "y", 50, LocalDateTime.now());
                File c = new File(home, "c", 50, LocalDateTime.now());
                Directory pictures = new Directory(home, "pictures", 0, LocalDateTime.now());
                File a = new File(pictures, "a", 50, LocalDateTime.now());
                File b = new File(pictures, "b", 50, LocalDateTime.now());
                Link d = new Link(root, "Link to pictures dir", 0, LocalDateTime.now(), pictures);
                Link e = new Link(root, "Link to file x", 0, LocalDateTime.now(), x);
                assertEquals(d.getTarget(), pictures);
                assertEquals(e.getTargetSize(), x.getSize());
                assertEquals(root.getTotalSize(), 250);
            }));
        }
        threads.forEach(Thread::start);

        threads.forEach(t -> {
            try {
                t.interrupt();
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
