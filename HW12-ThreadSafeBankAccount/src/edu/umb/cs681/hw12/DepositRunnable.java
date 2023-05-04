package edu.umb.cs681.hw12;

import java.time.Duration;

public class DepositRunnable implements Runnable{
	private BankAccount account;
	private volatile boolean done = false;

	public DepositRunnable(BankAccount account) {
		this.account = account;
	}

	public void stop(){
		done = true;
	}

	public void run(){
		try{
			while(!done){
				account.deposit(100);
				Thread.sleep( 2000 );
			}
		}catch(InterruptedException exception){}
	}
}

