/**
 * 
 */
package com.gc.threads;

/**
 * @author Maurice
 *
 */
public class ThreadDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main Start");
		
		task1 one = new task1();
		task1 two = new task1();
		
		runDMC dmc = new runDMC();
		
		Thread three = new Thread(dmc);
		
		one.start();
		two.start();
		three.start();
		
		System.out.println("Main End");

	}
	
	public static class runDMC implements Runnable{

		@Override
		public void run() {
				System.out.println("Running...." + Thread.currentThread().getName());
		}

		
		
	}
	
	/*
	 * 
	 */
	public static class task1 extends Thread{

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			System.out.println("Running...." + Thread.currentThread().getName());
		}
		
	}

}
