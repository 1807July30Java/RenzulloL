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

public class EmployeeInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		EmployeeDAO ed = new EmployeeDAOImpl();
		ObjectMapper om = new ObjectMapper();
		if(session!= null) {
			Employee E = null;
			String expenseId = req.getParameter("ExpId");
			if(expenseId != null) { 
				E= ed.getExpenseManager(Integer.parseInt(expenseId));
			}
			else {
				E = ed.getEmployeeByUsername((String)session.getAttribute("username"));
			}
			pw.write(om.writeValueAsString(E));
		}
		//req.getRequestDispatcher("expensePage").forward(req, resp);
	}
	
}