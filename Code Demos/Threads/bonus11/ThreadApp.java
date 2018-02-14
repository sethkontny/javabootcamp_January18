/**
 * 
 */
package com.gc.bonus11;

/**
 * Bonus Lab 11 - Threads
 * Main driver programs
 * 
 * @author maurice tedder
 *
 */
public class ThreadApp {

	/**
	 * @param Command line parameters
	 */
	public static void main(String[] args) {
		
		ThreadRunner hare = new ThreadRunner("Hare", 90, 100);
		ThreadRunner tortoise = new ThreadRunner("Tortoise", 0, 10);
		
		Thread hareThread = new Thread(hare);
		Thread tortoiseThread = new Thread(tortoise);
		
		//start the race
		hareThread.start();
		tortoiseThread.start();
	}
	
	/**
	 * Called by other Threads when they complete. And declares race winner, then interrupts the losing thread.
	 */
	public void finish(){
		//TODO
	}

}
