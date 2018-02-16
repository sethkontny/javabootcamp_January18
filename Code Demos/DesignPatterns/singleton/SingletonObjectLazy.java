/**
 * 
 */
package com.gc.singleton;

/**
 * Demo of Singleton design pattern - Lazy Initialization
 * @author maurice tedder
 *
 */
public class SingletonObjectLazy {
	private static SingletonObjectLazy instance;
	
	private double testValue;
	
	/*
	 * Private constructor
	 */
	private SingletonObjectLazy(){
		testValue =  Math.random();
	}
	
	/*
	 * Singleton getter
	 */
	public static SingletonObjectLazy getInstance(){
		if(instance == null){
			instance = new SingletonObjectLazy();
		}
		return instance;
	}
	
	/*
	 * Do Stuff method
	 */
	public void showMessage() {
		System.out.println("I'm lazy singleton (" + testValue + ")");
	}
}
