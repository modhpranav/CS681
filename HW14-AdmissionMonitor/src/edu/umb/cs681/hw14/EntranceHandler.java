package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicBoolean;

class EntranceHandler implements Runnable {
    private AdmissionMonitor monitor;

    public AtomicBoolean done = new AtomicBoolean(false);

    public EntranceHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void setDone() {
        done.getAndSet(true);
    }

    public void run() {
        while (monitor.isMonitoring) {
            try {
                if (done.get()) {
                    System.out.println(" Entrance terminated!");
                    break;
                }
                monitor.enter();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}