package com.eng.gp.project.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eng.gp.project.services.ProjectTrackingService;
import com.eng.gp.project.services.ProjectTrackingServiceBean;

/**
 * Servlet implementation class DeleteProject
 */
@WebServlet("/deleteProjectByProjectId")
public class DeleteProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		long projectId = Long.parseLong((request.getParameter("projectId")));
		ProjectTrackingService  service = new ProjectTrackingServiceBean();
		boolean result = service.deleteProject(projectId);
		
		/*if(result){
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/projectslist.jsp");
			requestDispatcher.forward(request, response);
		}else{
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
		}*/
	}catch(Exception exception){
		exception.printStackTrace();
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
