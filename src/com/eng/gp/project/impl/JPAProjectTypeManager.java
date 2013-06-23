package com.eng.gp.project.impl;




import org.hibernate.Session;

import com.eng.gp.project.entity.ProjectTypeEntity;
import com.eng.gp.project.managers.ProjectTypeManager;
import com.learn.hibrenate.util.HibernateConnection;

public class JPAProjectTypeManager implements ProjectTypeManager {

	@Override
	public ProjectTypeEntity findByPrimaryKey(long projectId) {
		
		Session session = HibernateConnection.getSession();
		 session.getTransaction().begin();
		  
			org.hibernate.Query q = session.createQuery("from ProjectTypeEntity b where b.projectTypeId="+projectId);
			System.out.println(q);
			ProjectTypeEntity pojectTypeEntity = (ProjectTypeEntity) q.uniqueResult();
		session.getTransaction().commit();
		
		
		return pojectTypeEntity;
	}

	/*@Override
	public ProjectTypeEntity persist(ProjectTypeEntity projectType) {
	
		return null;
	}*/

	

}
