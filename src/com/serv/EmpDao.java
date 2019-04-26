package com.serv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {
	
	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practise","root","root"); 
		} catch (Exception e) {
         System.out.println(e);		
         }  
		return con;	
		}
	public static int save(Emp e) {
		int status=0;
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps= con.prepareStatement("insert into Employee(NAME,PASSWORD,EMAIL,COUNTRY) value(?,?,?,?) ");
			ps.setString(1,e.getUsername());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
            status=ps.executeUpdate();  
            con.close();

		}catch(Exception exp) {
			System.out.println(exp);
		}
		return status;
	}
	public static int update(Emp e) {
		int status=0;
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update Employee set NAME=?,PASSWORD=?,EMAIL=?,COUNTRY=? where Id=?");
			ps.setString(1,e.getUsername());  
            ps.setString(2,e.getPassword());  
            ps.setString(3,e.getEmail());  
            ps.setString(4,e.getCountry());  
            ps.setInt(5,e.getId());  
            status=ps.executeUpdate();
            con.close();
		}catch(Exception exp) {
			System.out.println(exp);
		}
		return status;
	}
	public static int delete(int Id) {
		int status=0;
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from Employee where Id=?");
			ps.setInt(1,Id);
		    status=ps.executeUpdate();
			con.close(); 
		}catch(Exception exp) {
			System.out.println(exp);
		}
		return status;
	}
	public static Emp getEmployeeById(int Id) {
		Emp e=new Emp();
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from Employee where Id=?");
			ps.setInt(1,Id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				e.setId(rs.getInt(1));
				e.setUsername(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}
			con.close();
		}catch(Exception exp) {
			System.out.println(exp);
		}
		return e;
	}
	public static List<Emp> getAllEmployees(){
		List<Emp> EmpList=new ArrayList<Emp>();
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from Employee");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Emp e=new Emp();
				e.setId(rs.getInt(1));
				e.setUsername(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
				EmpList.add(e);
			}
		}catch(Exception exp) {
			System.out.println(exp);
		}
		return EmpList;
	}

}
