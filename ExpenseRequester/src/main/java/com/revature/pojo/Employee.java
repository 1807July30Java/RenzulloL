package com.revature.pojo;

public class Employee {
	private int EmployeeId;
	private int ManagerId;
	private String Email;
	private String firstname;
	private String lastname;
	private int password;
	public Employee(int EmployeeId, int ManagerId,String Email, String firstname, String lastname, int password){
		this.EmployeeId = EmployeeId;
		this.setManagerId(ManagerId); 
		this.setEmail(Email);
		this.setFname(firstname);
		this.setLname(lastname);
		this.password = password;
	}
	public int getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}
	public int getPassword() {
		return password;
	}

	public String getFname() {
		return firstname;
	}
	public void setFname(String fname) {
		this.firstname = fname;
	}
	public String getLname() {
		return lastname;
	}
	public void setLname(String name) {
		this.lastname = name;
	}
	public int getManagerId() {
		return ManagerId;
	}
	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	

}
