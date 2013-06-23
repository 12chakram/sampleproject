package com.eng.gp.project.services;

import java.util.List;

import com.eng.gp.project.domain.ProjectTrackingItem;
import com.eng.gp.project.domain.ProjectType;
import com.eng.gp.project.domain.Role;
import com.eng.gp.project.domain.exception.InvalidProjectException;


public interface ProjectTrackingService {
	
	public void saveProject(ProjectTrackingItem projectTracking) throws Exception;

	public List<ProjectTrackingItem> getAllProjects();

	public int removeAllProjects();

	public List<ProjectType> getAllProjectTypes();
	
	public boolean deleteProject(Long projectId) throws InvalidProjectException;

	public List<Role> getRoles();

}
