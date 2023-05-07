package edu.umb.cs681.hw18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccessCounterTest {

    @Test
    public void testSingletonInstance() {
        AccessCounter instance1 = AccessCounter.getInstance();
        AccessCounter instance2 = AccessCounter.getInstance();

        assertSame(instance1, instance2);
    }
}
