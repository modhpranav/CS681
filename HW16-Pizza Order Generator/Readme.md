# DIY HW16: Pizza Order Generator

## Description
This is a program that helps you create a pizza order by adding your desired toppings and generates an order for you. The toppings are stored in an array and a loop is used to create the order.

## Original Code
The original pizza order generator code is not safe to use in a multi-threaded environment because it uses an array to store toppings, which can lead to conflicts between multiple threads trying to access and modify the toppings at the same time. This can result in missing toppings or duplicate toppings in the final order. Additionally, the loop that generates the order can have a race condition if a thread modifies the numToppings variable while it is being looped through, leading to unexpected results.

## Revised Code
To make sure that the program works correctly when multiple threads are using it, I made some changes to the code. Instead of using a simple string array to store toppings, I used an ArrayList which is a type of data structure that can be safely accessed by multiple threads at the same time. I also added a ReentrantLock to protect the toppings list and numToppings variable from being accessed by multiple threads at once. This lock ensures that only one thread can access the toppings list and numToppings variable at a time. The addTopping() and generateOrder() methods are now enclosed in the lock() and unlock() methods of the ReentrantLock to guarantee that only one thread can access or modify them at any given time.

## How to Use
To use this pizza order generator, you just need to create a PizzaOrderGenerator object and use its addTopping() method to add toppings. To generate an order with the chosen toppings, use the generateOrder() method. The revised code ensures that even if multiple people are using it at the same time, everything will work properly without any issues.

## Conclusion
When we use data structures and mechanisms that are designed to handle multiple threads accessing the same code, we can prevent issues like race conditions in our HW. This ensures that our program will work correctly even when multiple threads are running at the same time.