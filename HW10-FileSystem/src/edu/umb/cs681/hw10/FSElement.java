package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
    protected Directory parent;
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected final ReentrantLock lock = new ReentrantLock();

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public void setParent(Directory parent) {
        lock.lock();
        try {
            this.parent = parent;
        } finally {
            lock.unlock();
        }
    }

    public Directory getParent() {
        lock.lock();
        try {
            return parent;
        } finally {
            lock.unlock();
        }
    }

    public List<FSElement> getChildren() {
        lock.lock();
        try {
            return Collections.unmodifiableList(this.getChildren());
        } finally {
            lock.unlock();
        }
    }

    public void setName(String name) {
        lock.lock();
        try {
            this.name = name;
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        lock.lock();
        try {
            return name;
        } finally {
            lock.unlock();
        }
    }

    public void setSize(int size) {
        lock.lock();
        try {
            this.size = size;
        } finally {
            lock.unlock();
        }
    }

    public int getSize() {
        lock.lock();
        try {
                if (this.isDirectory()) {
                    int totalSize = 0;
                    for (FSElement element : this.getChildren()) {
                        if (element instanceof Directory) {
                            totalSize += ((Directory) element).getTotalSize();
                        } else if (element instanceof File) {
                            totalSize += element.getSize();
                        }
                    }
                    return totalSize;
                } else {
                    return this.size;
                }
        } finally {
            lock.unlock();
        }
    }

    private boolean isFile() {
        if (this instanceof File)
            return true;
        else return false;
    }

    public boolean isDirectory() {return false;}

    public boolean isLink() {
        if(this instanceof Link)
            return true;
        else return false;
    }

    public LocalDateTime getCreationTime() {
        lock.lock();
        try {
            return creationTime;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
    }

}
