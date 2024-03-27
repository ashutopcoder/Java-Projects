package com.collage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Users {
public static void AddUsers(Scanner sc,Connection conn,String role) {
	System.out.println("Enter"+role+" A Name : ");
	String name=sc.next();
	System.out.println("Enter"+role+" Your Address : ");
	String address=sc.next();
	System.out.println("Enter"+role+" Your MobileNumber : ");
	String mobile =sc.next();
	System.out.println("Enter"+role+" Subject : ");
	String course=sc.next();
	try {
		String str="INSERT INTO users(name,address,mobile,course,role)VALUE(?,?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(str);
		ps.setString(1,name);
		ps.setString(2, address);
		ps.setString(3,mobile);
		ps.setString(4,course);
		ps.setString(5,role);
		int ar=ps.executeUpdate();
		if(ar>0) {
			System.out.println("Add"+role+" Successfully.");
		}
		else
			System.out.println("Add"+role+"Failed!");
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public static void ViewUser(Connection conn,String role) {
	try {
	String sql="SELECT * FROM users where role=?";
	PreparedStatement pst=conn.prepareStatement(sql);
	pst.setString(1, role);
	ResultSet rs=pst.executeQuery();
	while(rs.next()) {
	System.out.println("ID Number : "+rs.getInt(1));
	System.out.println("Name : "+rs.getString(2));
	System.out.println("Address : "+rs.getString(3));
	System.out.println("MobileNumber : "+rs.getString(4));
	System.out.println("Course : "+rs.getString(5));
	System.out.println("Designation : "+rs.getString(6));
	System.out.println("------------------------------------------");
	}
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public static void UpdateResult(Scanner sc,Connection conn,int u_id) {
	System.out.println("English Marks : ");
	int english=sc.nextInt();
	System.out.println("Hindi Marks : ");
	int hindi=sc.nextInt();
	System.out.println("Maths Marks : ");
	int maths=sc.nextInt();
	System.out.println("Computer Marks : ");
	int computer=sc.nextInt();
	System.out.println("Science Marks : ");
	int science=sc.nextInt();
	try {
		String sql="INSERT INTO result(u_id,english,hindi,maths,computer,science)VALUE(?,?,?,?,?,?)";
		PreparedStatement ps =conn.prepareStatement(sql);
		ps.setInt(1, u_id);
		ps.setInt(2,english);
		ps.setInt(3,hindi);
		ps.setInt(4,maths);
		ps.setInt(5,computer);
		ps.setInt(6,science);
		int ar=ps.executeUpdate();
		if(ar>0) {
			System.out.println("Result Updated Successfully !");
		}
		else
			System.out.println("Result Updation failed");
	}catch(Exception e)
	{
		e.printStackTrace();
	}
} 
public static boolean CheckStudent(int id ,Connection conn) {
	try {
		String sql="SELECT * FROM users where id =? and role='Student'";
		PreparedStatement ps =conn.prepareStatement(sql);
		ps.setInt(1,id);
		ResultSet rs =ps.executeQuery();
		int count=0;
		while(rs.next()) {
			count++;
		}
		if(count==0)
			return false;
		else
			return true;
	}catch(Exception e) {
		e.printStackTrace();
	}
	return true;
}
public static void ViewResult(Connection conn, int id) {
	try {
		String sql="SELECT name,course,hindi,english,maths,computer,science FROM users JOIN result on u_id=id WHERE u_id=?";
		PreparedStatement pst =conn.prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs =pst.executeQuery();
		if(rs.next()) {
			System.out.println("Name : "+rs.getString(1));
			System.out.println("Course : "+rs.getString(2));
			System.out.println("Hindi : "+rs.getInt(3));
			System.out.println("English : "+rs.getInt(4));
			System.out.println("Maths : "+rs.getInt(5));
			System.out.println("Computer : "+rs.getInt(6));
			System.out.println("Science : "+rs.getInt(7));
		}
		int total=0;
		for(int i=3;i<=7;i++) {
			total=total+(int)rs.getInt(i);
		}
		System.out.println("Total Marks : "+total);
		System.out.println("Percentage : "+total/5);
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}