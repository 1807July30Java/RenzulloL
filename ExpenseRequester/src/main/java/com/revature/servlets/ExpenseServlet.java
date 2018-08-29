package com.revature.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.ExpenseDAO;
import com.revature.dao.ExpenseDAOImpl;
import com.revature.pojo.Employee;
import com.revature.pojo.Expense;

@MultipartConfig
public class ExpenseServlet extends HttpServlet {
	private ExpenseDAO exd = new ExpenseDAOImpl();
	private EmployeeDAO ed = new EmployeeDAOImpl();
	private ObjectMapper om = new ObjectMapper();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
		Employee E = ed.getEmployeeByUsername((String)session.getAttribute("username"));
		if(session != null && E!= null) {
			resp.setContentType("application/json");
			PrintWriter pw = resp.getWriter();
			List<Expense> el = exd.getEmployeeExpenses(E.getEmployeeId());
			String r = om.writeValueAsString(el);
			pw.write(r);
		}
		else {
			resp.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String expdt = req.getParameter("expensedate");
		LocalDate ld = LocalDate.parse(expdt);
		java.sql.Date d = java.sql.Date.valueOf(ld);
		int a = Integer.parseInt(req.getParameter("expenseamount"));
		String description = req.getParameter("expensedescription");
		 Part file = req.getPart("imagefile");
         InputStream blob = file.getInputStream();
         HttpSession session = req.getSession(false);
         if(session!=null) {
			Employee e = ed.getEmployeeByUsername((String)session.getAttribute("username"));
			int eid = e.getEmployeeId();
			exd.newExpense(d, a, eid,e.getManagerId(),description,blob);
			//req.getRequestDispatcher("expensePage").forward(req, resp);
			resp.sendRedirect("expensePage");
		}
	}
}
