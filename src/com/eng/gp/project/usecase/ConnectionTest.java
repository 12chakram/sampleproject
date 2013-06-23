package com.eng.gp.project.usecase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.eng.gp.project.data.Users;

import com.learn.hibrenate.util.HibernateConnection;



public class ConnectionTest {

	
	
	public static void main(String[] args) {
		
		Users users = new Users();
		users.setDays("sun,Mon,Tue");
		users.setEmailid("kmail.com");
		users.setMobile("9849889255");
		users.setPassword("password");
		users.setUserName("kumar");
		addUser(users);
	}

	public static void addUser(Users user){
		
		Session session = HibernateConnection.getSession();
		  Transaction transaction = session.beginTransaction();
		  
		  session.save(user);
		  
		  transaction.commit();

	}
}
