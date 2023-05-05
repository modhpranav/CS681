package edu.umb.cs681.hw14;

class EntranceHandler implements Runnable {
    private AdmissionMonitor monitor;

    public EntranceHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (monitor.isMonitoring) {
            try {
                monitor.enter();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}