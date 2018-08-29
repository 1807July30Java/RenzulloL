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

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("Register.html").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO ed = new EmployeeDAOImpl();
		HttpSession session = request.getSession(true);	
		String username = request.getParameter("email");
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String managername = request.getParameter("managername");
		String pass = request.getParameter("password");
		Employee E = ed.getEmployeeByUsername(managername);
		if(E != null) {
			if(ed.newEmployee(username,fname,lname,pass, E.getEmployeeId())) {
				session.setAttribute("username", username);
				response.sendRedirect("expensePage");
			}
			else {
				response.sendRedirect("register");
			}
		}
		else {
			response.sendRedirect("register");
		}
	}

}
