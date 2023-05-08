package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T> {
    private LinkedList<Observer> observers;
    private ReentrantLock lockObs = new ReentrantLock();

    Observable() {
        observers = new LinkedList<>();
    }
    public void addObserver(Observer<T> o) {
        observers.add(o);
    }

    public void clearObservers() {
        observers.clear();

    }
    public LinkedList<Observer> getObservers(){
        return observers;
    }

    public int countObservers() {
        return observers.size();

    }
    public void removeObserver(Observer<T> o) {
        observers.remove(o);
    }

    public void notifyObservers(T event) {
        lockObs.lock();
        observers.forEach( (observer)->{observer.update(this, event);} );
        lockObs.unlock();
    }
}
