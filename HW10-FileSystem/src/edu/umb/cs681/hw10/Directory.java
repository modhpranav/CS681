package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement {
    private final List<FSElement> children = new LinkedList<>();
    private final List<Directory> directories = new LinkedList<>();
    private final List<File> files = new LinkedList<>();

    private final List<Link> links = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        if (parent != null) {
            parent.appendChild(this);
        }
    }

    public boolean isDirectory() {
        return true;
    }

    public List<FSElement> getChildren(){
        lock.lock();
        try {
            LinkedList<FSElement> childrenList = new LinkedList<>();
            for (FSElement element : children) {
                childrenList.add(element);
            }
            return Collections.unmodifiableList(childrenList);
        } finally {
            lock.unlock();
        }
    }


    public void appendChild(FSElement child) {
        lock.lock();
        try {
            children.add(child);
            if (child.isDirectory()) {
                directories.add((Directory) child);
            } else if (child.isLink()) {
                links.add((Link) child);
            } else {
                files.add((File) child);
            }
        } finally {
            lock.unlock();
        }
    }

    public int countChildren() {
        lock.lock();
        try {
            return children.size();
        } finally {
            lock.unlock();
        }
    }

    public List<Directory> getSubDirectories() {
        lock.lock();
        try {
            return Collections.unmodifiableList(directories);
        } finally {
            lock.unlock();
        }
    }

    public List<File> getFiles() {
        lock.lock();
        try {
            return Collections.unmodifiableList(files);
        } finally {
            lock.unlock();
        }
    }

    public int getTotalSize() {
        lock.lock();
        try {
            int totalSize = 0;
            for (FSElement element : children) {
                totalSize += element.getSize();
            }
            return totalSize;
        } finally {
            lock.unlock();
        }
    }
}
