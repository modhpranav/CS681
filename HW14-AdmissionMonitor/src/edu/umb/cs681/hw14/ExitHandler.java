package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicBoolean;



class ExitHandler implements Runnable {
    private AdmissionMonitor monitor;

    public AtomicBoolean done = new AtomicBoolean(false);

    public ExitHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void setDone() {
        done.getAndSet(true);
    }

    public void run() {
        while (monitor.isMonitoring) {
            try {
                if (done.get()) {
                    System.out.println(" Exit terminated!");
                    break;
                }
                monitor.exit();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}