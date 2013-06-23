package com.eng.gp.project.data;

import java.util.ArrayList;
import java.util.List;



public class ProjectTypes {
	
private  List<Projects> projects = new ArrayList<Projects>();




public  List<Projects> getProjects() {
 
	Projects project = new Projects();
	for(int i=0; i<=5; i++){
		
	 project.setProjectId(12345);
	 project.setProjectName("VFD");
	 
	 projects.add(project);
	 
	}
	return projects;
}

public void setProjects(List<Projects> projects) {
	this.projects = projects;
}	 
	
	
	

}
