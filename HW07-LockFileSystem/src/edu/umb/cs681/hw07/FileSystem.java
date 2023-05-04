package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static FileSystem filesystem = null;
    private static final ReentrantLock relock = new ReentrantLock();

    private FileSystem() {
    }

    public static FileSystem getFileSystem() {
        relock.lock();
        try {
            if (filesystem == null) {
                filesystem = new FileSystem();
            }
            return filesystem;
        } finally {
            relock.unlock();
        }
    }

    public static void main(String[] args) {
    }
}