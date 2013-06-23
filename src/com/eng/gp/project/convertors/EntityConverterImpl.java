package com.eng.gp.project.convertors;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.eng.gp.project.domain.ProjectTrackingItem;
import com.eng.gp.project.domain.Role;
import com.eng.gp.project.entity.PremisesEntity;
import com.eng.gp.project.entity.ProjectTrackingItemEntity;
import com.eng.gp.project.entity.RoleEntity;
import com.eng.gp.project.util.date.DateFormats;
import com.eng.gp.project.util.date.DateFormatter;
import com.eng.gp.project.util.date.LocalDateTime;
import com.eng.gp.project.util.date.TimeZones;

public class EntityConverterImpl implements EntityConverter{

	@Override
	public ProjectTrackingItem fromEntity(
			ProjectTrackingItemEntity projectEntity) {

		if (projectEntity == null) {
			return null;
		} 
		try{
			PremisesEntity premises = projectEntity.getPremises();
			TimeZone tz = TimeZone.getTimeZone(premises.getTimeZone());
			Calendar today = Calendar.getInstance();
			LocalDateTime today_utc = new LocalDateTime(today.getTimeInMillis(),TimeZones.UTC);
			LocalDateTime premisesLocaldate = new LocalDateTime(today_utc.instantInUtc(), tz);
			Date siteDate = DateFormatter.parse(DateFormats.DATE_FORMAT_PROJECT_SERVICE_DATE,premisesLocaldate.toString());

			Date dbStartDate = null;
			Date dbEndDate = null;
			LocalDateTime startDate = null;
			LocalDateTime endDate = null;

			if(projectEntity.getStartDate()!=null){
				dbStartDate = projectEntity.getStartDate();
			}
			if(projectEntity.getEndDate()!=null){
				dbEndDate = projectEntity.getEndDate();
			}

			ProjectTrackingItem project = new ProjectTrackingItem();
			if (dbStartDate != null) {

				startDate = new LocalDateTime(dbStartDate.getTime(), tz);
				//startDate = startDate.valueOf(startDate.toString().substring(0, startDate.toString().indexOf(" ")));
				dbStartDate = DateFormatter.parse(DateFormats.DATE_FORMAT_PROJECT_SERVICE_DATE,startDate.toString());
				if (dbEndDate != null) {
					endDate = new LocalDateTime(dbEndDate.getTime(), tz);
					//endDate = endDate.valueOf(endDate.toString().substring(0, endDate.toString().indexOf(" ")));
					dbEndDate =  DateFormatter.parse(DateFormats.DATE_FORMAT_PROJECT_SERVICE_DATE,endDate.toString());
				}
				
				int startDiff =0;
				int endDiff =0;
				
				if(startDate!=null)startDiff = startDate.compareTo(premisesLocaldate);
				if(endDate!=null)endDiff = endDate.compareTo(premisesLocaldate);

				if (dbStartDate.after(siteDate)) {
					project.setProjectStatus("PLANNED");
				} else if ((dbStartDate.before(siteDate) || dbStartDate.equals(siteDate))
						&& (dbEndDate == null || dbEndDate.after(siteDate) || dbEndDate.equals(siteDate))) {
					project.setProjectStatus("PROGRESS");
				} else if (dbEndDate != null && dbEndDate.before(siteDate)) {
					project.setProjectStatus("COMPLETED");
				}

				if(startDiff>0){
					project.setProjectStatus("PLANNED");
				} else if((startDiff<0 || startDiff == 0) && (endDiff>0 || endDiff == 0 )){
					project.setProjectStatus("PROGRESS");
				} else if(endDiff<0){
					project.setProjectStatus("COMPLETED");
				}
			} else{
				project.setProjectStatus("PENDING");
			}

			project.setProjectName(projectEntity.getProjectName());
			project.setProjectId(projectEntity.getProjectId());
			if(dbStartDate!=null)project.setStartDate(dbStartDate);
			if(dbEndDate!=null)project.setEndDate(dbEndDate);
			project.setChannels(projectEntity.getChannels());
			project.setPremisesId(projectEntity.getPremises().getPremisesId());
			project.setProjectTypeId(projectEntity.getProjectType().getProjectTypeId());

			return project;
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return null;
	
		
	}

	@Override
	public Role fromEntity(RoleEntity roleentity) {
		if (roleentity == null) {
			return null;
		} 
		Role role = new Role();
		role.setRoleId(roleentity.getRoleId());
		role.setName(roleentity.getName());
		role.setDescription(roleentity.getDescription());
		return role;
	}

}
