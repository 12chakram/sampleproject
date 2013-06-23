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
import com.eng.gp.project.domain.Role;
import com.eng.gp.project.services.ProjectTrackingService;
import com.eng.gp.project.services.ProjectTrackingServiceBean;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProjectTrackingService  service = new ProjectTrackingServiceBean();
		
		String userName = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		
		if(userName!=null && !userName.isEmpty() && userName.equalsIgnoreCase("compuGain") && 
				password!=null && !password.isEmpty()&& password.equals("Password1")){
		
			List<ProjectType> projectslist;
			projectslist = service.getAllProjectTypes();
			
			request.setAttribute("allProjectstypes", projectslist);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/project.jsp");
			requestDispatcher.forward(request, response);

	}else{
		List<Role> roleList;
		roleList = service.getRoles();
		request.setAttribute("roles", roleList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/register.jsp");
		requestDispatcher.forward(request, response);
	}
 }
}