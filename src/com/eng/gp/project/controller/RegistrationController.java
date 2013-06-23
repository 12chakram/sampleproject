package com.eng.gp.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eng.gp.project.domain.EndUser;
import com.eng.gp.project.services.EndUserService;
import com.eng.gp.project.services.EndUserServiceBean;
import com.eng.gp.project.util.date.Language;
import com.eng.gp.project.util.date.MeasurementSystem;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
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
		
		String userName = request.getParameter("userNsame");
		String password = request.getParameter("password");
		String confirmpw = request.getParameter("confirmpw");
		String roleId = request.getParameter("userRole");
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String language = request.getParameter("language");
		String measurementSystem =request.getParameter("measurementSystem");
		
		EndUser user = new EndUser();
		user.setUsername(userName);
		if(password.equalsIgnoreCase(confirmpw)){
		user.setPassword(password);
		}
		user.setRole(Long.parseLong(roleId));
		user.setEmail(email);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setLanguage(language);
		user.setMeasurementSystem(MeasurementSystem.US_STANDARD);
		
		EndUserService  userService = new  EndUserServiceBean();
		try {
			userService.createUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
