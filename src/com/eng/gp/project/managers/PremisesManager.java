package com.eng.gp.project.managers;

import com.eng.gp.project.entity.PremisesEntity;

public interface PremisesManager {

   
    PremisesEntity findByPrimaryKey(long premisesId);
    
   
}
