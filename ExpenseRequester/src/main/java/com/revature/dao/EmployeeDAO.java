package com.revature.dao;

import java.util.List;

import com.revature.pojo.Employee;

public interface EmployeeDAO {
	public Employee getEmployeeById(int EmployeeId);
	public List<Employee> getManagedEmployees(int EmployeeId);
	public Employee getEmployeeByLogin(String Username,String Password);
	public Employee getEmployeeByUsername(String username);
	public boolean newEmployee(String username, String fname, String lname, String password, int managerid);
	public boolean updateEmployeeName(int employeeid,String firstname, String lastname)  ;
	public boolean updateEmployeePassword(int employeeid, String password);
	public boolean updateEmployeeEmail(int employeeid,String email);
	public Employee getExpenseManager(int expenseid);
	public boolean updateEmployeeTempPassword(int employeeid, int password);
	public Employee getEmployeeByTempLogin(String name, int Password);
}
