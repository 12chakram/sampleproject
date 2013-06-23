package com.eng.gp.project.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.eng.gp.project.entity.EndUserEntity;
import com.eng.gp.project.managers.EndUserManager;
import com.learn.hibrenate.util.HibernateConnection;

public class JPAEndUserManager implements EndUserManager{
	
	private static final Logger log = Logger.getLogger(JPAEndUserManager.class);

	@Override
	public void persist(EndUserEntity user) throws Exception {
		
		try{
			Session session = HibernateConnection.getSession();
			 session.getTransaction().begin();
			session.save(user);
			session.getTransaction().commit();
			}catch(Exception exception){
				log.debug(exception.getLocalizedMessage());
			}
	}

}
