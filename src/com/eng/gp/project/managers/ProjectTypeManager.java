package com.eng.gp.project.managers;

import com.eng.gp.project.entity.ProjectTypeEntity;



public interface ProjectTypeManager {
	
	ProjectTypeEntity findByPrimaryKey(long projectId);
	
	//ProjectTypeEntity persist(ProjectTypeEntity projectType);
}
