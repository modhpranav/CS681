package edu.umb.cs681.hw14;

class ExitHandler implements Runnable {
    private AdmissionMonitor monitor;

    public ExitHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (monitor.isMonitoring) {
            try {
                monitor.exit();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}