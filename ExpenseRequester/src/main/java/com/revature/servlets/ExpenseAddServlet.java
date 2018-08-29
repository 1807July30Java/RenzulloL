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
import com.revature.pojo.Expense;

public class ExpenseAddServlet extends HttpServlet {
	ExpenseDAO ex = new ExpenseDAOImpl();
	EmployeeDAO em = new EmployeeDAOImpl();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		int ExpenseId = Integer.parseInt(req.getParameter("ExpenseId"));
		int val = Integer.parseInt(req.getParameter("val"));
		String mngr = (String)session.getAttribute("username");
		Expense Exp = ex.getExpenseById(ExpenseId);
		Employee Mg = em.getEmployeeByUsername(mngr);
		Employee E = em.getEmployeeById(Exp.getEmployeeId());
		if(val ==1 && E.getManagerId() == Mg.getEmployeeId()) {
			if(ex.approveExpense(ExpenseId)) {
				System.out.println("approved");
				pw.write(req.getParameter("ExpenseId"));
				}
		}
		else if(val == 2 && E.getManagerId() == Mg.getEmployeeId()) {
			if(ex.denyExpense(ExpenseId)) {
				System.out.println("denied");
				pw.write(req.getParameter("ExpenseId"));
				}
		}
		//req.getRequestDispatcher("expensePage").forward(req, resp);
	}
	
}