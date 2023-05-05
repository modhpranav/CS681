# DIY HW17: DeadLockSafeCode

## Description
This is a program that demonstrates a common scenario in multi-threaded applications where two threads need to use multiple resources at the same time.

## Original Code
The original code provided is not safe for multiple threads and can lead to a situation where both threads are waiting for each other to release the resources they need. Hence, the situation of a deadlock occurs.

## Revised Code
In the Revised code, We first acquire one lock and then try to acquire the other lock. If we are unable to acquire the other lock, we release the first lock. We repeat the process for the other lock as well. This way, we ensure that both threads acquire the locks in the same order and avoid the possibility of a deadlock.

## How to Use
To use this DIY example, simply run the OriginalCode file and the main method will execute, creating a deadlock that will cause the code to never terminate. The revised code works in the same way, but you will notice in the terminal that it finishes automatically once the execution is complete.

## Conclusion
In summary, to avoid deadlocks in multi-threaded applications, we should make sure that all threads acquire locks in a consistent order and release them as soon as possible. We can use the ReentrantLock object to control the locking mechanism more flexibly and avoid deadlocks.