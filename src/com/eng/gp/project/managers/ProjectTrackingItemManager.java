package com.eng.gp.project.managers;



import java.util.List;

import com.eng.gp.project.domain.ProjectType;
import com.eng.gp.project.entity.ProjectTrackingItemEntity;



public interface ProjectTrackingItemManager {

	void persist(ProjectTrackingItemEntity project) throws Exception;

	List<ProjectTrackingItemEntity> getAllProjects();

	int removeAllProjects();

	List<ProjectType> getAllProjectTypes();

	ProjectTrackingItemEntity findByPrimaryKey(Long projectId);

	ProjectTrackingItemEntity upDateProject(ProjectTrackingItemEntity entity);

	boolean removeById(Long projectId);
	
	/*ProjectTrackingItemEntity merge( ProjectTrackingItemEntity project );
	
	ProjectTrackingItemEntity findByPrimaryKey(long projectId);
	
	List<ProjectTrackingItemEntity> getAllProjectsBypremisesId(Long premisesId);
	
	boolean removeById(Serializable id);*/
	
}
