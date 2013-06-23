package com.eng.gp.project.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.eng.gp.project.entity.ProjectTypeEntity;
import com.eng.gp.project.entity.RoleEntity;
import com.eng.gp.project.managers.RoleManager;
import com.learn.hibrenate.util.HibernateConnection;

public class JPARoleManager implements RoleManager {

	

	@Override
	public List<RoleEntity> getAllRoles() {
		
		Session session = HibernateConnection.getSession();
		session.getTransaction().begin();
		String queryString = "from RoleEntity r";
	    Query q = session.createQuery(queryString);
	    session.getTransaction().commit();
	    return q.list();
	}

	@Override
	public RoleEntity findByPrimaryKey(long roleId) {
		
		Session session = HibernateConnection.getSession();
		 session.getTransaction().begin();
			org.hibernate.Query q = session.createQuery("from RoleEntity b where b.roleId="+roleId);
			System.out.println(q);
			RoleEntity roleEntity = (RoleEntity) q.uniqueResult();
		session.getTransaction().commit();
		return roleEntity;
	}
}
