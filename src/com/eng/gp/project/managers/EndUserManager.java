package com.eng.gp.project.managers;

import com.eng.gp.project.entity.EndUserEntity;

public interface EndUserManager {

	void persist(EndUserEntity user) throws Exception;
}
