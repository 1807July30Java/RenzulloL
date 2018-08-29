package com.revature.dao;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import com.revature.pojo.Expense;

public interface ExpenseDAO {
	public Expense getExpenseById(int EmployeeId);
	public List<Expense> getEmployeeExpenses(int EmployeeId);
	public List<Expense> getAllExpenses();
	public void newExpense(Date date, int amount,int EmployeeId,int managerid,String desc,InputStream blob);
	public boolean approveExpense(int ExpenseId);
	public boolean denyExpense(int ExpenseId);
	public boolean uploadImage(int Expenseid, InputStream blob);
	public byte[] getImage(int ExpenseId);
}
