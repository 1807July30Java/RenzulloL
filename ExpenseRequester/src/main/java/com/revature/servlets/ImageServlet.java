package com.revature.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ExpenseDAO;
import com.revature.dao.ExpenseDAOImpl;
import com.revature.pojo.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImageServlet extends HttpServlet {
	ExpenseDAO exd = new ExpenseDAOImpl();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
		if(session!= null) {
			int expid = Integer.parseInt(req.getParameter("expid"));
			resp.setContentType("image/jpg");
			byte[] I = exd.getImage(expid);
			if(I!= null) {
				resp.getOutputStream().write(I);
			}
		}
		else {
			resp.sendRedirect("login");
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	
	}
}