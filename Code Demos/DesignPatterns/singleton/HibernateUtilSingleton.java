package com.gc.singleton;

public class HibernateUtilSingleton {
	
	/**
	 * static instance variable of this class
	 */
	private static SessionFactory singleton = buildSessionFacotry();
		
	/**
	 * Private constructor prevents user from instantiating and instance of this singleton
	 */
	private HibernateUtilSingleton(){
		
	}
	
	/**
	 * Contains boilerplate code to create a hibernate SessionFactory
	 * @return Hibernate SessionFactory object
	 */
	private static SessionFactory buildSessionFacotry() {
		// TODO Create sessionfactory object here
		return null;
	}

	/**
	 * Returns SessionFactory singleton object
	 * @return Singleton instance of HibernateUtilSingleton object
	 */
	public static SessionFactory getInstance(){
		return singleton;
	}
}
