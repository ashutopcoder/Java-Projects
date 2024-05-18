package com.market;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class Users {
	
	//  Login For AdminPortal
	
	public static void Admin(Scanner sc,Connection conn,String role) {
		System.out.print("\nEnter Email : ");
		String email=sc.next();
		System.out.print("\nEnter Password : ");
		String pass=sc.next();
		try {
			String sql = "SELECT * FROM users WHERE email=? AND password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,email);
			ps.setString(2,pass);
			int flag=1;
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				while(flag==1) {
				System.out.println("1. Add Product");
				System.out.println("2. Update Product");
				System.out.println("3. Delete Product");
				System.out.println("4. Unactive Costumer");
				System.out.println("5. Active Costumer");
				System.out.println("6. View Orders");
				System.out.println("7. View Product");
				System.out.println("8. Exit");
				System.out.println("------------------------");
				System.out.print("\nEnter Choice : ");
				int choice = sc.nextInt();
				switch(choice) {
				case 1:
					Products.AddProduct(sc,conn);
					break;
				case 2:
					Products.UpdateProduct(sc,conn);
					break;
				case 3:
					Products.DeleteProduct(sc,conn);
					break;
				case 4:
					Users.Unactive(sc,conn);
					break;
				case 5:
					Users.Active(sc, conn);
					break;
				case 6:
					Products.ViewOrdersAdmin(conn);
					break;
				case 7:
					Products.ViewProduct(conn);
					break;
				case 8:
					System.out.print("\n.....Bye Bye .....");
					flag=0;
					break;
				default :
					System.out.println("\nTry Again!");
				}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Customer Login
	
	public static void CustomerLogin(Scanner sc,Connection conn,String role) {
		System.out.print("\nEnter Email : ");
		String email=sc.next();
		System.out.print("\nEnter Password : ");
		String pass =sc.next();
		try {
			String sql = "SELECT * FROM users WHERE email=? AND password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,email);
			ps.setString(2,pass);
			int flag=1;
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int uid = rs.getInt(1);
				while(flag==1) {
				System.out.println("\n-----------------------");
				System.out.println("1. View Product");
				System.out.println("2. Buy Product");
				System.out.println("3. View Orders");
				System.out.println("4. Exit");
				System.out.println("-----------------------");
				System.out.print("\nEnter Your Choice : ");
				int choice=sc.nextInt();
				switch(choice) {
				case 1:
					Products.ViewProduct(conn);
					break;
				case 2: 
					Products.BuyProduct(conn,sc,uid);
					break;
				case 3:
					Products.ViewOrderUser(conn,uid);
					break;
				case 4:
					System.out.println("<---Bye Bye--->");
					flag=0;
				}
			}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Costumer SignupPortal
	
		public static void CustomerSignup(Scanner sc,Connection conn,String role,String status) {
		System.out.println("Enter First Name : ");
		String fname=sc.next();
		System.out.println("Enter Last Name : ");
		String lname=sc.next();
		System.out.println("Enter Mobile Number: ");
		String mobile=sc.next();
		System.out.println("Enter Unique Email : ");
		String email=sc.next();
		System.out.println("Enter Password  : ");
		String pass=sc.next();
		try{
			String sql="INSERT INTO users(fname,lname,mobile,email,password,role,status) VALUE(?,?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,fname);
			pst.setString(2,lname);
			pst.setString(3,mobile);
			pst.setString(4,email);
			pst.setString(5,pass);
			pst.setString(6,role);
			pst.setString(7,status);
			pst.executeUpdate();
			if(true)
				System.out.println("\nCostumer Added Successfully.");
			else
				System.out.println("Costumer Added Failed.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
		// UNACTIVE COSTUMERS 
	public static void Unactive(Scanner sc,Connection conn) {
		ViewUsers(conn);
		System.out.println("Enter Costumer Id Which You Want To Unactive : ");
		int id=sc.nextInt();
		try {
			String sql="UPDATE users SET status='Unactive' WHERE u_id=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			if(true)
				System.out.println("User Unactive SuccessFully .");
			else
				System.out.println("User Unactive Failed .");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// ACTIVE COSTUMERS 
public static void Active(Scanner sc,Connection conn) {
	ViewUsers(conn);
	System.out.println("Enter Costumer Id Which You Want To Active : ");
	int id=sc.nextInt();
	try {
		String sql="UPDATE users SET status='Active' WHERE u_id=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		if(true)
			System.out.println("User Active SuccessFully .");
		else
			System.out.println("User Active Failed .");
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}
	public static void ViewUsers(Connection conn) {
		System.out.println("<----Our Costumer---->");
		try {
			String sql="SELECT * FROM users WHERE role='costumer'";
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				System.out.println("User id : "+rs.getInt(1));
				System.out.println("First Name : "+rs.getString(2));
				System.out.println("Last Name : "+rs.getString(3));
				System.out.println("Mobile No : "+rs.getString(4));
				System.out.println("Email : "+rs.getString(5));
				System.out.println("Role : "+rs.getString(6));
				System.out.println("Status : "+rs.getString(8));
				System.out.println("-------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

}