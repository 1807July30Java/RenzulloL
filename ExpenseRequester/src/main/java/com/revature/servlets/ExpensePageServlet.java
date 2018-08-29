package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExpensePageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
		try{
			String username = (String)session.getAttribute("username");
			if(session!= null && username != null) {
				req.getRequestDispatcher("ExpensePage.html").forward(req, resp);
			}
			else {
				resp.sendRedirect("login");
			}
		}catch (NullPointerException e) {
			resp.sendRedirect("login");
		}
	}
	

	//protected static void doPostt(HttpServletRequest req, HttpServletResponse resp) {
		
	//}
}
