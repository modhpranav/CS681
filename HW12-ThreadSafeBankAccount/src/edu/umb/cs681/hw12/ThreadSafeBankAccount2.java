package edu.umb.cs681.hw12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getName() +
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().getName() +
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getName() +
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getName() +
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().getName() +
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getName() +
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() { return this.balance; }

	public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount2 = new ThreadSafeBankAccount2();
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 5; i++){
			DepositRunnable depositRunnable = new DepositRunnable(bankAccount2);
			WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bankAccount2);
			threads.add(new Thread(depositRunnable));
			threads.add(new Thread(withdrawRunnable));
			threads.get(i*2).start();
			threads.get(i*2 + 1).start();
			try {
				Thread.sleep( 2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			depositRunnable.stop();
			withdrawRunnable.stop();
			threads.get(i*2).interrupt();
			try {
				threads.get(i*2).join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			try {
				threads.get(i*2 + 1).join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
