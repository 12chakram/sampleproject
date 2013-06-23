package com.eng.gp.project.usecase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.eng.gp.project.domain.ProjectTrackingItem;
import com.eng.gp.project.services.ProjectTrackingService;
import com.eng.gp.project.services.ProjectTrackingServiceBean;
import com.eng.gp.project.util.date.TimeZones;


public class ProjectTrackingUsecase {
 
	public static void main(String[] args) throws Exception {
		
		
		long projectTypeId= 123456789;
		long premisesId =88506;
		
		ProjectTrackingService  service = new ProjectTrackingServiceBean();
		
		ProjectTrackingItem projectTracking = new ProjectTrackingItem();
		
/*		Calendar calendar = new GregorianCalendar(TimeZones.UTC);

		DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");    
		formatter.setTimeZone(TimeZones.UTC);  

		String newZealandTime = formatter.format(calendar.getTime());
*/
		
		
	
		
		projectTracking.setProjectName("TestProject");
		projectTracking.setProjectTypeId(projectTypeId);
		projectTracking.setPremisesId(premisesId);
		projectTracking.setStartDate(new Date(2013-04-01));
		projectTracking.setEndDate(new Date(2013-05-30));
		projectTracking.setChannels("test1,test2");
		
		
		
		service.saveProject(projectTracking);
		
	}

	
}
