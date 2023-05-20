HW17: DeadLockSafeDIY

-> Description
This program simulates a pizza topping order scenario where two threads represent different toppings being added to the pizza. It demonstrates a common challenge in multi-threaded applications where multiple threads need to access shared resources concurrently.

-> Original Code
The original code provided for the pizza topping order example is not safe for multiple threads. It can lead to a situation where both threads are waiting for each other to release the locks on the resources they need, resulting in a deadlock.

-> Revised Code
In the revised code, we ensure that both threads acquire the locks in the same order to avoid a potential deadlock. By following a consistent locking order, the threads can access the shared resources without getting stuck in a deadlock scenario.

-> How to Use
To use this example, simply run the OriginalCode.java file. It will execute the main method, which creates two threads representing different pizza toppings. However, you will notice that the program never terminates due to the deadlock situation.

To observe the revised code in action, run the RevisedCode.java file. This version of the code resolves the deadlock issue by acquiring the locks in the same order for both threads. As a result, the program finishes execution automatically once the toppings are added.

-> Conclusion
In multi-threaded applications, it is crucial to manage shared resources properly to avoid deadlocks. By enforcing a consistent locking order and releasing locks promptly, we can prevent deadlock situations. The use of ReentrantLock objects provides more flexibility and control over the locking mechanism, improving the safety of concurrent programs.