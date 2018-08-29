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


public class SettingsServlet extends HttpServlet {
	EmployeeDAO ed = new EmployeeDAOImpl();
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
						String password = req.getParameter("newpassword");
						if(ed.updateEmployeePassword(E.getEmployeeId(),password)){
							resp.sendRedirect("expensePage");
						}
						break;
				}
			}
			
			
		}
		else {
			resp.sendRedirect("login");
		}
		//req.getRequestDispatcher("expensePage").forward(req, resp);
	}
	
}