/**
 * 
 */
package com.gc.libterm.util;

import java.net.URI;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Maurice Tedder
 * This code is based on code from
 * Ref:http://docs.jboss.org/hibernate/core/3.3/reference/en/html/tutorial.html#tutorial-firstapp-helpers
 *
 * This is a singleton needed to prevent recreating connections that exceed the heroku postges DB connection limit
 */
public class HibernateUtil {

	
	private static SessionFactory factory = buildSessionFacotry();
	
	/**
	 * Private constructor
	 */
	private HibernateUtil() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Build session factory
	 */
	private static SessionFactory buildSessionFacotry(){
		try {
			//String DATABASE_URL="mysql://username:password@DBServerURL:3306/DataBaseName";			
			
			//System.out.println(System.getenv("DATABASE_URL"));//
			//System.out.println("#################:" + System.getProperty("DATABASE_URL"));//tomcat on AWS
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			URI uri = new URI(System.getProperty("DATABASE_URL"));//Database URL stored in environment variable for Tomcat AWS environments
			//URI uri = new URI(System.getenv("DATABASE_URL"));//Database URL stored in environment variable for Java SE environments
			//URI uri = new URI(DATABASE_URL);//for localhost. No environment variables			
					
			String[] userInfo = uri.getUserInfo().split(":");// get username and
																// password from
																// uri string
			String username = userInfo[0];
			String password = userInfo[1];
			
			
			//System.out.println("username:" + username + "," + password);
			
			String dbURL = "jdbc:mysql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();//Use this part to run PostGres locally + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

			cfg.setProperty("hibernate.connection.url", dbURL);
			cfg.setProperty("hibernate.connection.username", username);
			cfg.setProperty("hibernate.connection.password", password);			
			return cfg.configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}		
	}
	/*
	 * Method controlling access to sesssion foactory
	 */
	public static SessionFactory getSessionFactory(){		
		return factory;
	}
}
