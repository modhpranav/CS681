package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static FileSystem instance = null;
    private static final ReentrantLock lock = new ReentrantLock();

    private FileSystem() {
    }

    public static FileSystem getFileSystem() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new FileSystem();
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
    }
}