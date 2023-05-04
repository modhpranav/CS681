package edu.umb.cs681.hw09;
import java.util.concurrent.atomic.AtomicReference;

public class Aircraft {
    private final AtomicReference<Position> position = new AtomicReference<>();

    public Aircraft(Position position) {
        this.position.set(position);
    }

    public void setPosition(double newLat, double newLon, double newAlt) {
        this.position.updateAndGet(p -> p.change(newLat, newLon, newAlt));
    }

    public Position getPosition() {
        return position.get();
    }
}

