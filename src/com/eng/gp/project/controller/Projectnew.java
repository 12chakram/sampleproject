package com.eng.gp.project.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eng.gp.project.domain.ProjectTrackingItem;
import com.eng.gp.project.services.ProjectTrackingService;
import com.eng.gp.project.services.ProjectTrackingServiceBean;
import com.eng.gp.project.util.date.LocalDateTime;
import com.eng.gp.project.util.date.StringTodate;

/**
 * Servlet implementation class Project
 */
@WebServlet("/createProjectnew")
public class Projectnew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Projectnew() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long premisesId =3L;
		ProjectTrackingService  service = new ProjectTrackingServiceBean();
		ProjectTrackingItem projectTracking = new ProjectTrackingItem();
		try{
			String projectName = request.getParameter("projectname");
		String projectTypeid = request.getParameter("projecttype");
		String listInvstName = request.getParameter("listInvstName");
		
		String sdate = request.getParameter("start").replace("/", "-");
		//sdate = sdate.replace("/", "-");
		   String edate = request.getParameter("end").replace("/", "-");;
		//edate = edate.replace("/", "-");
		Date pstartDate = null;
		Date pendDate = null;
		
		if(sdate!=null && !sdate.isEmpty() && !sdate.contains(" ")){
			pstartDate = new Date(StringTodate.stringToDate(sdate));
		}
		if(edate!=null && !edate.isEmpty() && !sdate.contains(" ")){
			pendDate = new Date (StringTodate.stringToEndDate(edate));
		}
		
		LocalDateTime startDate = null;
		LocalDateTime endDate  = null;
		if(pstartDate!=null){
			startDate = LocalDateTime.forUtc(pstartDate.getTime());
		}if(pendDate!=null){
			endDate = LocalDateTime.forUtc(pendDate.getTime());
		}
		projectTracking.setProjectName(projectName);
		projectTracking.setProjectTypeId(Long.parseLong(projectTypeid));
		projectTracking.setPremisesId(premisesId);
		projectTracking.setChannels(listInvstName.toString());
		projectTracking.setStartDate(new Date(startDate.instantInUtc()));
		projectTracking.setEndDate(new Date (endDate.instantInUtc()));
		
		service.saveProject(projectTracking);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/sucess.jsp");
		requestDispatcher.forward(request, response);
		System.out.println("yessss");
		
			}catch(Exception exception){
				exception.printStackTrace();
		
			}
		
		}

}
