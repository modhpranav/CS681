package edu.umb.cs681.hw12;

import java.time.Duration;

public class WithdrawRunnable implements Runnable{
	private BankAccount account;
	private volatile boolean done = false;

	public WithdrawRunnable(BankAccount account) {
		this.account = account;
	}

	public void stop(){
		done = true;
	}

	public void run(){
		try{
			while(!done){
				account.withdraw(100);
				Thread.sleep( 2000 );
			}
		}catch(InterruptedException exception){}
	}
}

