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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ManagerServlet extends HttpServlet {
	private ObjectMapper om = new ObjectMapper();
	EmployeeDAO ed = new EmployeeDAOImpl(); 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
		if(session!= null) {
			resp.setContentType("application/json");
			PrintWriter pw = resp.getWriter();
			String username = (String) session.getAttribute("username");
			Employee e = ed.getEmployeeByUsername(username);
			//error: for some reason first name does not get set
			String r = om.writeValueAsString(ed.getManagedEmployees(e.getEmployeeId()));
			pw.write(r);
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req,resp);
	}

}