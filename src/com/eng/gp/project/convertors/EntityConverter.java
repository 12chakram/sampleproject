package com.eng.gp.project.convertors;

import com.eng.gp.project.domain.ProjectTrackingItem;
import com.eng.gp.project.domain.Role;
import com.eng.gp.project.entity.ProjectTrackingItemEntity;
import com.eng.gp.project.entity.RoleEntity;

public interface EntityConverter {
	
	ProjectTrackingItem fromEntity(ProjectTrackingItemEntity projectEntity);

	Role fromEntity(RoleEntity roleentity);

}
