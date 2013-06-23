package com.eng.gp.project.managers;

import java.util.List;

import com.eng.gp.project.entity.RoleEntity;


public interface RoleManager {
	
	List<RoleEntity> getAllRoles();

	RoleEntity findByPrimaryKey(long roleId);

}
