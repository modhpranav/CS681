package edu.umb.cs681.hw10;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static FileSystem instance = null;
    private LinkedList<Directory> rootDirs;
    private static final ReentrantLock lock = new ReentrantLock();

    private FileSystem() {
        this.rootDirs = new LinkedList<Directory>();
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

    public List<Directory> getRootDirs(){
        lock.lock();
        try {
            return Collections.unmodifiableList(rootDirs);
        } finally {
            lock.unlock();
        }
    }

    public void appendRootDir(Directory root) {
        lock.lock();
        try {
            rootDirs.add(root);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
    }
}
