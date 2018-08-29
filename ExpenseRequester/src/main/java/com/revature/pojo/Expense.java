package com.revature.pojo;

import java.time.LocalDate;
import java.util.Date;

public class Expense {
	private int ExpenseId;
	private int EmployeeId;
	private int managerId;
	private Date date;
	private int amount;
	private int approved;
	private String description;
	public Expense(int ExpenseId,int EmployeeId, int ManagerId, Date date,int amount, int approved,String description) {
		// TODO Auto-generated constructor stub
		this.setExpenseId(ExpenseId);
		this.setEmployeeId(EmployeeId);
		this.setDate(date);
		this.setAmount(amount);
		this.setApproved(approved);
		this.setManagerId(ManagerId);
		this.setDescription(description);
	}
	public int getExpenseId() {
		return ExpenseId;
	}
	public void setExpenseId(int expenseId) {
		ExpenseId = expenseId;
	}
	public int getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date2) {
		this.date = date2;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
