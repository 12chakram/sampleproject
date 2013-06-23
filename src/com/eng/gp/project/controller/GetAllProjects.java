package com.eng.gp.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eng.gp.project.domain.ProjectTrackingItem;
import com.eng.gp.project.domain.ProjectType;
import com.eng.gp.project.services.ProjectTrackingService;
import com.eng.gp.project.services.ProjectTrackingServiceBean;

/**
 * Servlet implementation class GetAllProjects
 */
@WebServlet("/getAllProjects")
public class GetAllProjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetAllProjects() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectTrackingService  service = new ProjectTrackingServiceBean();
		List<ProjectTrackingItem> projects =service.getAllProjects();
	
		List<ProjectType> projectslist = new ArrayList<ProjectType>();
		projectslist = service.getAllProjectTypes();
		request.setAttribute("allProjectstypes", projectslist);
		request.setAttribute("allProjects", projects);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/projectslist.jsp");
		requestDispatcher.forward(request, response);
	}
	
}
