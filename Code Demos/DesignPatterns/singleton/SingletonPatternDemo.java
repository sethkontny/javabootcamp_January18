package com.gc.singleton;
/*
 * Ref: http://www.tutorialspoint.com/design_pattern/mvc_pattern.htm
 */
public class SingletonPatternDemo {
	   public static void main(String[] args) {

	      //illegal construct
	      //Compile Time Error: The constructor SingleObject() is not visible
//	      SingleObject object = new SingleObject();

	      //Get the only object available - Eager initialization	   
	      SingletonObjectEager object1a = SingletonObjectEager.getInstance();

	      //show the message
	      object1a.showMessage();
	      
	      //Do it again - should have the same random number
	      SingletonObjectEager object1b = SingletonObjectEager.getInstance();
	      
	      //show the message
	      object1b.showMessage();
	      
	      //Get the only object available - Eager initialization	   
	      SingletonObjectLazy object2a = SingletonObjectLazy.getInstance();

	      //show the message
	      object2a.showMessage();
	      
	    //Do it again - should have the same random number	   
	      SingletonObjectLazy object2b = SingletonObjectLazy.getInstance();

	      //show the message
	      object2b.showMessage();		      
	      	     
	   }
}
