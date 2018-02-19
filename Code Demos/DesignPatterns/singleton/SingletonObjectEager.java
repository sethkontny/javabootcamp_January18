/**
 * 
 */
package com.gc.singleton;

/**
 * Demo of Singleton design pattern - Eager Initialization
 * @author maurice tedder
 *
 */
public class SingletonObjectEager {
	private static SingletonObjectEager instance = new SingletonObjectEager();
	private double testValue;
	
	/*
	 * Private constructor
	 */
	private SingletonObjectEager(){
		testValue =  Math.random();
	}
	
	/*
	 * Singleton getter
	 */
	public static SingletonObjectEager getInstance(){
		return instance;
	}
	
	/*
	 * Do Stuff method
	 */
	public void showMessage() {
		System.out.println("I am an eager singleton! (" + testValue + ")");
	}	
}
