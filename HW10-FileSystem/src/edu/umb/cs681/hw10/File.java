package edu.umb.cs681.hw10;

import java.time.LocalDateTime;

public class File extends FSElement {

    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        if (parent != null) {
            parent.appendChild(this);
        }
    }

    public boolean isDirectory() {return false;}

    public boolean isFile() {return true;}

    public int getSize() {
        lock.lock();
        try {
            return size;
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
}
