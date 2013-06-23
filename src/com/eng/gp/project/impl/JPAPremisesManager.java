package com.eng.gp.project.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.eng.gp.project.entity.PremisesEntity;
import com.eng.gp.project.managers.PremisesManager;
import com.learn.hibrenate.util.HibernateConnection;

public class JPAPremisesManager implements PremisesManager {

	@Override
	public  PremisesEntity findByPrimaryKey(long premisesId) {
	
		Session session = HibernateConnection.getSession();
		  Transaction transaction = session.beginTransaction();
		  
			org.hibernate.Query q = session.createQuery("from PremisesEntity b where b.premisesId="+premisesId);
			System.out.println(q);
			PremisesEntity premisesEntity = (PremisesEntity) q.uniqueResult();
		session.getTransaction().commit();
		
		
		return premisesEntity;
	}
	

}
