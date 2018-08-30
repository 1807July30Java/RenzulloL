package com.revature.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.pojo.Employee;
import com.revature.services.EmailService;


public class SettingsServlet extends HttpServlet {
	EmployeeDAO ed = new EmployeeDAOImpl();
	EmailService Em = new EmailService();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
		if(session!= null) {
			resp.sendRedirect("Settings.html");
		}
		else {
			resp.sendRedirect("login");
		}
		//req.getRequestDispatcher("expensePage").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
		
		if(session!= null ) {
			String username = (String)session.getAttribute("username");
			Employee E = ed.getEmployeeByUsername(username);
			String operation = req.getParameter("op");
			if(operation != null&& operation.length()>0) {
				
				switch(operation) {
					case "name":
						String firstname = req.getParameter("newfirstname");
						String lastname = req.getParameter("newlastname");
						if(ed.updateEmployeeName(E.getEmployeeId(),firstname , lastname)){
							resp.sendRedirect("expensePage");
						}
						break;
					case "email":
						String email = req.getParameter("newemail");
						if(ed.updateEmployeeEmail(E.getEmployeeId(),email)){
							session.setAttribute("username",email);
							resp.sendRedirect("expensePage");
						}
						break;
					case "password":
						int temppass = (int)Math.floor(Math.random() * 1000000000) + 1;
						Em.sendEmail("login.properties", Integer.toString(temppass) , (String)session.getAttribute("username"));
						String currentpassword = req.getParameter("currentpassword");
						Employee doublechecklogin = ed.getEmployeeByLogin((String)session.getAttribute("username"), currentpassword);
						if(doublechecklogin != null) {
							if(ed.updateEmployeeTempPassword(E.getEmployeeId(),temppass)){
								resp.sendRedirect("login");
							}
						}
						break;
					case "authpassword":
						String password = req.getParameter("newpassword");
						if(ed.updateEmployeePassword(E.getEmployeeId(),password) && ed.updateEmployeeTempPassword(E.getEmployeeId(), 0)){
							resp.sendRedirect("expensePage");
						}
						break;
						
				}
			}
			
			
		}
		else {
			String invalidUsername = req.getParameter("email").toLowerCase();
			System.out.println("requested pass reset from " + invalidUsername);
			if(req.getParameter("op").equals("loginhelp") && invalidUsername != null) {
				int temppass = (int)Math.floor(Math.random() * 1000000000) + 1;
				Employee doublecheckusername = ed.getEmployeeByUsername(invalidUsername);
				if(doublecheckusername != null) {
					Em.sendEmail("login.properties", Integer.toString(temppass) , invalidUsername);
					System.out.print("Providing Temporary password: " + temppass + " for " + invalidUsername);
					if(ed.updateEmployeeTempPassword(doublecheckusername.getEmployeeId(),temppass)){
						resp.sendRedirect("login");
					}
				}
			}
			else {
				resp.sendRedirect("login");
			}
		}
		//req.getRequestDispatcher("expensePage").forward(req, resp);
	}
	
}