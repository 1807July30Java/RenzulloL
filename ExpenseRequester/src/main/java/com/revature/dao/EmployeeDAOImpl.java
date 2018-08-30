package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {
	String filename = "connection.properties";
	public EmployeeDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Employee getEmployeeById(int EmployeeId) { 
		Employee E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
	        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setInt(1, EmployeeId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	        	int id = rs.getInt("EMPLOYEE_ID");
                String email = rs.getString("EMAIL");
                String fname = rs.getString("FIRST_NAME");
                String lname = rs.getString("LAST_NAME");
                int password = rs.getInt("PASSWORD");
                int manager_id = rs.getInt("MANAGER_ID");
                E = new Employee(id, manager_id, email,fname,lname, password);
	        } else {
	            System.out.println("No Employees with that ID");
	        }
	        con.close();
	        return E;
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
}

	@Override
	public List<Employee> getManagedEmployees(int EmployeeId) {
		ArrayList<Employee> el = new ArrayList<Employee>();
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT * FROM EMPLOYEE WHERE MANAGER_ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, EmployeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	int id = rs.getInt("EMPLOYEE_ID");
                String email = rs.getString("EMAIL");
                String fname = rs.getString("FIRST_NAME");
                String lname = rs.getString("LAST_NAME");
                int password = rs.getInt("PASSWORD");
                int manager_id = rs.getInt("MANAGER_ID");
                el.add(new Employee(id, manager_id, email,fname,lname, password));
            }
            con.close();
            return el;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
	}
	public boolean newEmployee(String username, String fname, String lname, String password, int managerid) {
        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "INSERT INTO EMPLOYEE(EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,MANAGER_ID) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, fname);
            pstmt.setString(3, lname);
            pstmt.setInt(4, password.hashCode());
            pstmt.setInt(5, managerid);
            
            if(pstmt.executeUpdate()>0) {
            	return true;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public Employee getEmployeeByLogin(String name, String Password) {
		Employee E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT * FROM EMPLOYEE WHERE EMAIL = ? AND PASSWORD = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, Password.hashCode());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	int id = rs.getInt("EMPLOYEE_ID");
                String email = rs.getString("EMAIL");
                String fname = rs.getString("FIRST_NAME");
                String lname = rs.getString("LAST_NAME");
                int password = rs.getInt("PASSWORD");
                int manager_id = rs.getInt("MANAGER_ID");
                int temppass = rs.getInt("TEMP_PASSWORD");
                E = new Employee(id, manager_id, email,fname,lname, password);
                if(temppass != 0) {
                	E.setTempPass(temppass);
                }
            } else {
                System.out.println("No Employees with that username/password combo");
            }
            con.close();
            return E;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
        
    }
	public Employee getEmployeeByTempLogin(String name, int Password) {
		Employee E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT * FROM EMPLOYEE WHERE EMAIL = ? AND TEMP_PASSWORD = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, Password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	int id = rs.getInt("EMPLOYEE_ID");
                String email = rs.getString("EMAIL");
                String fname = rs.getString("FIRST_NAME");
                String lname = rs.getString("LAST_NAME");
                int password = rs.getInt("PASSWORD");
                int manager_id = rs.getInt("MANAGER_ID");
                int temppass = rs.getInt("TEMP_PASSWORD");
                E = new Employee(id, manager_id, email,fname,lname, password);
                if(temppass != 0) {
                	E.setTempPass(temppass);
                }
            } else {
                System.out.println("No Employees with that username/password combo");
            }
            con.close();
            return E;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
        
    }

	@Override
	public Employee getEmployeeByUsername(String username) {
		Employee E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT * FROM EMPLOYEE WHERE EMAIL = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("EMPLOYEE_ID");
                String email = rs.getString("EMAIL");
                String fname = rs.getString("FIRST_NAME");
                String lname = rs.getString("LAST_NAME");
                int password = rs.getInt("PASSWORD");
                int manager_id = rs.getInt("MANAGER_ID");
                int temppass = rs.getInt("TEMP_PASSWORD");
                E = new Employee(id, manager_id, email,fname,lname, password);
                if(temppass != 0) {
                	E.setTempPass(temppass);
                }
            } else {
                System.out.println("No Employees with that username");
            }
            con.close();
            return E;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
	}
	public boolean updateEmployeeName(int employeeid,String firstname, String lastname)  {
		Employee E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "UPDATE EMPLOYEE SET FIRST_NAME = ?, LAST_NAME = ? WHERE EMPLOYEE_ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setInt(3, employeeid);
            if (stmt.executeUpdate() > 0) {
                return true;
            } 
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
     
	}
	public boolean updateEmployeePassword(int employeeid, String password)  {
		Employee E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "UPDATE EMPLOYEE SET PASSWORD = ? WHERE EMPLOYEE_ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, password.hashCode());
            stmt.setInt(2, employeeid);
            if (stmt.executeUpdate() > 0) {
                return true;
            } 
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
     
	}
	public boolean updateEmployeeTempPassword(int employeeid, int password)  {
		Employee E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "UPDATE EMPLOYEE SET TEMP_PASSWORD = ? WHERE EMPLOYEE_ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, password);
            stmt.setInt(2, employeeid);
            if (stmt.executeUpdate() > 0) {
                return true;
            } 
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
     
	}
	public boolean updateEmployeeEmail(int employeeid,String email)  {
		Employee E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "UPDATE EMPLOYEE SET EMAIL = ? WHERE EMPLOYEE_ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,email.toLowerCase());
            stmt.setInt(2, employeeid);
            if (stmt.executeUpdate() > 0) {
                return true;
            } 
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
     
	}
    public Employee getExpenseManager(int expenseid) {
    	Employee E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT MANAGER_ID FROM EMPLOYEE WHERE EMPLOYEE_ID IN (SELECT EMPLOYEE_ID FROM EXPENSEREQ WHERE EXPENSE_ID = ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, expenseid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("MANAGER_ID");
                E = getEmployeeById(id);
            } else {
                System.out.println("No Expenses with that ID");
            }
            con.close();
            return E;
		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return E;
    }
		
	
}
