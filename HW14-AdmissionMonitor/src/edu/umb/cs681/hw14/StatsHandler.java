package edu.umb.cs681.hw14;

class StatsHandler implements Runnable {
    private AdmissionMonitor monitor;

    public StatsHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (monitor.isMonitoring) {
            try {
                int count = monitor.countCurrentVisitors();
//                System.out.println("Current visitors: " + count);
                Thread.sleep(1000); // update every second
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }
}