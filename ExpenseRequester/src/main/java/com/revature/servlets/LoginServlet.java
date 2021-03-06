package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.pojo.Employee;
import com.revature.services.AdminService;
import com.revature.services.AuthenticationService;
import com.revature.services.EmailService;



public class LoginServlet extends HttpServlet{
	EmployeeDAO ed = new EmployeeDAOImpl();
	/**
	 * 
	 */
	EmailService Em = new EmailService();
	private static final long serialVersionUID = 817105812389880890L;
	
	//return login page for GET request
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("Login.html").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("email").toLowerCase();
		String password = req.getParameter("password");
		Employee E = AuthenticationService.isValidUser(username,password);
		if(E != null) {
			//if(E.getTempPass() == 0) {
				HttpSession session = req.getSession(true);
				session.setAttribute("username", username);
				resp.sendRedirect("expensePage");
			//}else {
				//resp.sendRedirect("login");
			//}
		}
		else {
			int temppass = -1;
			try {
				temppass = Integer.parseInt(password);
				E = ed.getEmployeeByTempLogin(username, temppass);
				if(E!=null) {
					HttpSession session = req.getSession(true);
					session.setAttribute("username", username);
					resp.sendRedirect("PasswordUpdate.html");
				}else {
					resp.sendRedirect("register");
				}
			}catch(NumberFormatException e) {
				resp.sendRedirect("register");
			}
			
		}
		
	}	

}
