package com.revature.services;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.pojo.Employee;

public class AuthenticationService {
	public static Employee isValidUser(String username, String password) {
		EmployeeDAO ed = new EmployeeDAOImpl();
		if( username != null) {
			return ed.getEmployeeByLogin(username, password);
		}
		else {
			return null;
		}
	}
}
