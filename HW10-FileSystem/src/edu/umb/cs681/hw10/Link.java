package edu.umb.cs681.hw10;

import java.time.LocalDateTime;

public class Link extends FSElement {
    FSElement target;
    int size;
    public Link(Directory parent, String name, int size, LocalDateTime creationtime, FSElement target){
        super(parent, name, size, creationtime);
        this.size=0;
        this.target=target;
        if(parent!=null){
            parent.appendChild(this);}
    }

    public FSElement getTarget(){
        lock.lock();
        try {
            return this.target;
        } finally {
            lock.unlock();
        }
    }

    public int getSize() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }

    public int getTargetSize() {
        lock.lock();
        try {
            if (target instanceof Link)
                return ((Link) target).getTargetSize();
            else
                return target.getSize();
        } finally {
            lock.unlock();
        }
    }
}