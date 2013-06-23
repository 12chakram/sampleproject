package com.learn.hibrenate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateConnection {
	
	
	static Session session;
	
	public static Session getSession(){
		
	


		
		     try{
		    
		     if(session == null){
		    
		     SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
		     session = sessionfactory.openSession();
		     }
		    
		     }catch(Exception e){
		     e.printStackTrace();
		     }
		        return session;
	
	}
}