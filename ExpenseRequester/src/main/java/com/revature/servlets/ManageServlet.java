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
import com.revature.dao.ExpenseDAO;
import com.revature.dao.ExpenseDAOImpl;
import com.revature.pojo.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ManageServlet extends HttpServlet {
	private ObjectMapper om = new ObjectMapper();
	ExpenseDAO ex = new ExpenseDAOImpl(); 
	EmployeeDAO ed = new EmployeeDAOImpl();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String username = req.getQueryString();
		resp.setContentType("application/json");
		HttpSession session = req.getSession(false);
		PrintWriter pw = resp.getWriter();
		if(username != null && username.length() > 0) {
			Employee e = ed.getEmployeeByUsername(username);
			String r = om.writeValueAsString(ex.getEmployeeExpenses(e.getEmployeeId()));
			pw.write(r);
		}
		else {
			if(session!= null) {
				String a = om.writeValueAsString(ex.getAllExpenses());
				pw.write(a);
			}
			else {
				resp.sendRedirect("login");
				}
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req,resp);
	}

	//protected static void doPostt(HttpServletRequest req, HttpServletResponse resp) {
		
	//}
}