package com.eng.gp.project.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.eng.gp.project.domain.ProjectType;
import com.eng.gp.project.entity.ProjectTrackingItemEntity;
import com.eng.gp.project.entity.ProjectTypeEntity;
import com.eng.gp.project.managers.ProjectTrackingItemManager;
import com.learn.hibrenate.util.HibernateConnection;

public class JPAProjectTrackingItemManager implements ProjectTrackingItemManager {
	
	private static final Logger log = Logger.getLogger(JPAProjectTrackingItemManager.class);

	@Override
	public void persist(ProjectTrackingItemEntity project) {
		
		try{
		
		Session session = HibernateConnection.getSession();
		 session.getTransaction().begin();
		session.save(project);
		session.getTransaction().commit();
		}catch(Exception exception){
			log.debug(exception.getLocalizedMessage());
		}
}

	@Override
	public List<ProjectTrackingItemEntity> getAllProjects() {
		
		Session session = HibernateConnection.getSession();
		session.getTransaction().begin();
		String queryString = "from ProjectTrackingItemEntity p";
	    Query q = session.createQuery(queryString);
	    session.getTransaction().commit();
	    return q.list();
	  
	}

	@Override
	public int removeAllProjects() {
		
		Session session = HibernateConnection.getSession();
		session.getTransaction().begin();
		String queryString = "delete ProjectTrackingItemEntity where premises="+88506;
	    Query q = session.createQuery(queryString);
	    
	    int count = q.executeUpdate();
	    session.getTransaction().commit();
	    
	    return count;
		
	}

	@Override
	public List<ProjectType> getAllProjectTypes() {
		List<ProjectType> projectTypesList = new ArrayList<ProjectType>();
	    List<ProjectTypeEntity>projectTypeEntityList = null;
		
		Session session = HibernateConnection.getSession();
		session.getTransaction().begin();
		String queryString = "from ProjectTypeEntity pt";
	    Query q = session.createQuery(queryString);
	    projectTypeEntityList = q.list();
	    session.getTransaction().commit();

	    for(ProjectTypeEntity projectTypeEntity :projectTypeEntityList ){
		    ProjectType project = new ProjectType();
	    	project.setProjectTypeId(projectTypeEntity.getProjectTypeId());
	    	project.setProjectType(projectTypeEntity.getProjectType());
	    	projectTypesList.add(project);
	      }
	  
	  return projectTypesList;
		
	}

	@Override
	public ProjectTrackingItemEntity findByPrimaryKey(Long projectId) {
		Session session = HibernateConnection.getSession();
		session.getTransaction().begin();
	 return	(ProjectTrackingItemEntity) session.get(ProjectTrackingItemEntity.class, projectId);
		
	}

	@Override
	public ProjectTrackingItemEntity upDateProject(ProjectTrackingItemEntity entity) {
		
		Session session = HibernateConnection.getSession();
		session.getTransaction().begin();
		
		return (ProjectTrackingItemEntity) (session.merge(entity));
	}

	@Override
	public boolean removeById(Long projectId) {
		
		Session session = HibernateConnection.getSession();
		session.getTransaction().begin();
		String queryString = "delete ProjectTrackingItemEntity where projectId="+projectId;
	    Query q = session.createQuery(queryString);
	    
	    int count = q.executeUpdate();
	    session.getTransaction().commit();
	    if(count==1){
	    return true;
	    }else{
	    	return false;
	    }
	}

}
