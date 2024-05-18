package com.market;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Products {
	
	
	//ADDPRODUCT PORTAL
	
	public static void AddProduct(Scanner sc,Connection conn) {
		System.out.print("Enter Product Name : ");
		String pname = sc.next();
		System.out.print("Enter Product Quantity : ");
		int qty = sc.nextInt();
		System.out.print("Enter Product Price : ");
		int price = sc.nextInt();
		try {
			String sql = "INSERT INTO products(pname,qty,price) VALUE(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pname);
			ps.setInt(2, qty);
			ps.setInt(3, price);
			int ar = ps.executeUpdate();
			if(ar>0)
				System.out.println("Added Success");
			else
				System.out.println("Failed To Add");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//DELETE PRODUCT >>
	
	public static void DeleteProduct(Scanner sc,Connection conn) {
		ViewProduct(conn);
		System.out.println("\nEnter Product Id Which You Want To Delete : ");
		int id =sc.nextInt();
		try {
			String sql="DELETE FROM orders Where oid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			int ans=pst.executeUpdate();
			if(ans>0)
				System.out.println("Product Deleted Successfully ...");
			else
				System.out.println("Product Deleted Failed");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//VIEW ORDERS Portal
	
	public static void ViewOrders(Connection conn) {
		try {
			String sql="SELECT * from orders";
			PreparedStatement pst =conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				System.out.println("ProductName : "+rs.getString(1));
				System.out.println("Product Quantity : "+rs.getInt(2));
				System.out.println("Product ");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//UPDATE PRODUCT PORTAL
	
	public static void UpdateProduct(Scanner sc,Connection conn) {
		ViewProduct(conn);
		System.out.println("\nEnter Product Id Which You Want To Update : ");
		int id=sc.nextInt();
		System.out.print("\nEnter Updated Name : ");
		String name=sc.next();
		System.out.print("\nEnter Updated Quantity : ");
		int qty=sc.nextInt();
		System.out.print("\nEnter Updated Price : ");
		int price =sc.nextInt();
		try {
		String sql="UPDATE products SET pname=?, qty=?,price=? WHERE pid=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, name);
		pst.setInt(2, qty);
		pst.setInt(3, price);
		pst.setInt(4, id);
		int afrw=pst.executeUpdate();
		if(afrw>0)
			System.out.println("\nData Updated Successfully");
		else
			System.out.println("Data Updation Failed .");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void ViewOrdersAdmin(Connection conn) {
		System.out.println("<----- Orders ----->");
		try {
			String sql="SELECT orders.oid,users.fname,users.email,products.pname,orders.qty,products.price from orders JOIN users ON orders.u_id=users.u_id JOIN products ON orders.pid=products.pid";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println("Order Id : "+rs.getInt(1));
				System.out.println("User Name: " +rs.getString(2));
				System.out.println("Email : "+rs.getString(3));
				System.out.println("Product Name : "+rs.getString(4));
				System.out.println("Quantity : "+rs.getInt(5));
				System.out.println("Price : "+rs.getInt(6));
				System.out.println("----------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//for Costumers Products Buy,View Products,View Orders ------->
	public static void ViewProduct(Connection conn) {
		System.out.println("<-------- Our Products ------->");
		try {
			String sql="SELECT * FROM products";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println("Product Name : "+rs.getString(2));
				System.out.println("Product Id : "+rs.getInt(1));
				System.out.println("Product Price : "+rs.getInt(3));
				System.out.println("Product Quantity : "+rs.getInt(4));
				System.out.println("-----------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void BuyProduct(Connection conn,Scanner sc,Integer id) {
		ViewProduct(conn);
		System.out.println("<-----------Purchase Product---------->");
		
		System.out.println("Enter Product Id : ");
		int pid=sc.nextInt();
		try {
			String sql="SELECT * FROM products where pid=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				System.out.println("Enter Quantity : ");
				int qty=sc.nextInt();
					
						int newQty = rs.getInt(3)-qty;
						String que="UPDATE products SET qty=? WHERE pid=?;";
						PreparedStatement pstm=conn.prepareStatement(que);
						pstm.setInt(1,newQty);
						pstm.setInt(2, pid);
						pstm.executeUpdate();
						
						String s1="INSERT INTO orders(u_id,pid,qty) VALUE(?,?,?);";
						PreparedStatement pst=conn.prepareStatement(s1);
						pst.setInt(1, id);
						pst.setInt(2, pid);
						pst.setInt(3, qty);
						int afrw = pst.executeUpdate();
						if(afrw>0) {
							System.out.print("\nOrder Placed .");
						}
						else
							System.out.println("Order Placed Failed ");
			}else {
				System.out.println("Product Not Found!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void ViewOrderUser(Connection conn,int uid) {
		System.out.println("<--------Your Order------->");
		try {
			String sql="SELECT orders.oid,products.pname,orders.qty,products.price from orders JOIN users ON orders.u_id=users.u_id JOIN products ON orders.pid=products.pid WHERE users.u_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.out.print("\nOrder Id : "+rs.getInt(1));
				System.out.print("\nProduct Name : "+rs.getString(2));
				System.out.print("\nProduct Quantity : "+rs.getInt(3));
				System.out.print("\nProduct Price : "+rs.getInt(4));
				System.out.println("\n-----------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
