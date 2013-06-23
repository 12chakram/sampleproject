package com.eng.gp.project.services;

import com.eng.gp.project.domain.EndUser;
import com.eng.gp.project.entity.EndUserEntity;
import com.eng.gp.project.entity.RoleEntity;
import com.eng.gp.project.impl.JPAEndUserManager;
import com.eng.gp.project.impl.JPARoleManager;
import com.eng.gp.project.managers.EndUserManager;
import com.eng.gp.project.managers.RoleManager;
import com.eng.gp.project.util.date.Language;


public class EndUserServiceBean implements EndUserService{

	@Override
	public void createUser(EndUser endUser) throws Exception {
		RoleManager	roleManager = new JPARoleManager(); 
		EndUserManager endUserManager = new JPAEndUserManager();
		EndUserEntity user = new EndUserEntity();
		user.setUsername(endUser.getUsername());
		user.setPassword(endUser.getPassword());
		RoleEntity role = roleManager.findByPrimaryKey(endUser.getRole());
		if(!(role==null)){
			user.setRole(role);
		}
	
		user.setEmail(endUser.getEmail());
		user.setFirstName(endUser.getFirstName());
		user.setLastName(endUser.getLastName());
		if(endUser.getLanguage().equals("en_US")){
			user.setLanguage(Language.en_US);
		}
		user.setMeasurementSystem(endUser.getMeasurementSystem());
		endUserManager.persist(user);
	}
	

}
