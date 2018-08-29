package com.revature.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Expense;
import com.revature.util.ConnectionUtil;

public class ExpenseDAOImpl implements ExpenseDAO {
	String filename = "connection.properties";
	public ExpenseDAOImpl() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public Expense getExpenseById(int ExpenseId) {
		//using a Statement--beware injection
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
	        String sql = "SELECT * FROM EXPENSEREQ WHERE EXPENSE_ID = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setInt(1, ExpenseId);
	        ResultSet rs = stmt.executeQuery();
	        if(rs.next()) {
	            int id = rs.getInt("EXPENSE_ID");
	            int Eid = rs.getInt("EMPLOYEE_ID");
	            int Mid = rs.getInt("MANAGER_ID");
	            Date d = rs.getDate("EXPENSE_DATE");
	            int amount = rs.getInt("EXPENSE_AMOUNT");
	            int approved = rs.getInt("APPROVED");
	            String description = rs.getString("EXPENSE_DESCRIPTION");
	            return new Expense(id,Eid,Mid,d,amount,approved,description);
	        	}
	        	con.close();
		}
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
		
	}

	@Override
	public List<Expense> getEmployeeExpenses(int EmployeeId) {
		ArrayList<Expense> el = new ArrayList<Expense>();
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT * FROM EXPENSEREQ WHERE EMPLOYEE_ID = ? ORDER BY EXPENSE_DATE DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, EmployeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("EXPENSE_ID");
                int Eid = rs.getInt("EMPLOYEE_ID");
                int Mid = rs.getInt("MANAGER_ID");
                Date d = rs.getDate("EXPENSE_DATE");
                int amount = rs.getInt("EXPENSE_AMOUNT");
                int approved = rs.getInt("APPROVED");
                String description = rs.getString("EXPENSE_DESCRIPTION");
	            el.add(new Expense(id,Eid,Mid,d,amount,approved,description));
                
            }
            con.close();
            return el;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
	}
	
	public List<Expense> getAllExpenses() {
		ArrayList<Expense> el = new ArrayList<Expense>();
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT * FROM EXPENSEREQ WHERE APPROVED = 1 OR APPROVED = 2 ORDER BY EXPENSE_DATE DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("EXPENSE_ID");
                int Eid = rs.getInt("EMPLOYEE_ID");
                int Mid = rs.getInt("MANAGER_ID");
                Date d = rs.getDate("EXPENSE_DATE");
                int amount = rs.getInt("EXPENSE_AMOUNT");
                int approved = rs.getInt("APPROVED");
                String description = rs.getString("EXPENSE_DESCRIPTION");
	            el.add(new Expense(id,Eid,Mid,d,amount,approved,description));
           
            }
            con.close();
            return el;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
	}

	@Override
	public void newExpense(Date date, int amount, int EmployeeId,int managerid,String desc,InputStream blob){
		System.out.println("tried to add exp");
        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) { 
            String sql = "INSERT INTO EXPENSEREQ(EMPLOYEE_ID,MANAGER_ID,EXPENSE_DATE,EXPENSE_AMOUNT,EXPENSE_DESCRIPTION,IMAGEBLOB) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, EmployeeId);
            pstmt.setInt(2,managerid);
            pstmt.setDate(3,(java.sql.Date) date);
            pstmt.setInt(4,amount);
            pstmt.setString(5, desc);
            pstmt.setBlob(5,blob);
            pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
	
	public boolean approveExpense(int ExpenseId) {
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			System.out.println(ExpenseId);
            String sql = "UPDATE EXPENSEREQ SET APPROVED = 1 WHERE EXPENSE_ID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,ExpenseId);
            if(pstmt.executeUpdate()>0) {
            	System.out.println("true");
            	return true;
            }
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
	}
	public boolean denyExpense(int ExpenseId) {
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			System.out.println(ExpenseId);
            String sql = "UPDATE EXPENSEREQ SET APPROVED = 2 WHERE EXPENSE_ID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,ExpenseId);
            if(pstmt.executeUpdate()>0) {
            	System.out.println("true");
            	return true;
            }
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
	}
	public boolean uploadImage(int Expenseid, InputStream blob) {
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "UPDATE EXPENSEREQ SET IMAGEBLOB = ? WHERE EXPENSE_ID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setBlob(1, blob);
            pstmt.setInt(2,Expenseid);
            if(pstmt.executeUpdate()>0) {
            	System.out.println("true");
            	return true;
            }
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
	}
	public byte[] getImage(int ExpenseId) {
		byte[] im = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT IMAGEBLOB FROM EXPENSEREQ WHERE EXPENSE_ID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,ExpenseId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            	System.out.println("true");
            	im = rs.getBytes("IMAGEBLOB");
            	return im;
            }
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return im;
	}
	
}
