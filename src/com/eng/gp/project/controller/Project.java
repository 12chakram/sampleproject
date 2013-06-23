package com.eng.gp.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eng.gp.project.domain.ProjectType;
import com.eng.gp.project.services.ProjectTrackingService;
import com.eng.gp.project.services.ProjectTrackingServiceBean;

/**
 * Servlet implementation class Project
 */
@WebServlet("/createProject")
public class Project extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Project() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		
		if(userName!=null && !userName.isEmpty() && userName.equalsIgnoreCase("compuGain") && password!=null && !password.isEmpty()&& password.equals("Password1")){
			ProjectTrackingService  service = new ProjectTrackingServiceBean();
		
			List<ProjectType> projectslist;
			projectslist = service.getAllProjectTypes();
			
			request.setAttribute("allProjectstypes", projectslist);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/project.jsp");
			requestDispatcher.forward(request, response);

			System.out.println("yessss");
		}else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
