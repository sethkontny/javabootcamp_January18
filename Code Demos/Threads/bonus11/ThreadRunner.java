/**
 * 
 */
package com.gc.bonus11;

/**
 * Thread class for Bonus 11 lab
 * @author maurice tedder
 *
 */
public class ThreadRunner implements Runnable {

	/**
	 * Max distance of race
	 */
	private static final int METERS = 1000;
	/**
	 * Name of racer
	 */
	private String name;
	/**
	 * Meters per second speed of racer
	 */
	private int speed;

	/**
	 * Percentage of time to rest
	 */
	private int rest;
		
	/**
	 * 
	 * Parameterized Constructor
	 * 
	 * @param name - String Racer name
	 * @param speed - int Racer speed in meters/sec
	 * @param rest - int percentage of race to rest
	 */
	public ThreadRunner(String name, int speed, int rest) {
		super();
		this.name = name;
		this.speed = speed;
		this.rest = rest;
	}



	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		int distance = 0;
		
		while(distance  < METERS){
			
			int action = (int) ((Math.random() * 100) + 1);
			if(action < rest){//rest
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{//run
				distance += speed;
			}
		}
	}
}
