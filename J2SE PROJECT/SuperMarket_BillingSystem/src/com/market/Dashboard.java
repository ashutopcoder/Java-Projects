package com.market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Dashboard {
	private static Connection conn;
	private static Scanner sc=new Scanner(System.in); 
	public static void main(String args[]) {
		while(true) {
		try {
			
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/SuperMarket","root","2001");
				System.out.println("\n\nPress 1 To Admin ");
				System.out.println("Press 2 To Costumer Login  ");
				System.out.println("Press 3 To Costumer Signup ");
				System.out.println("Press 4 To Exit ");
				System.out.println("----------------------");
				System.out.print("\nEnter Your Choice : ");
				int choice =sc.nextInt();
				 switch(choice){
				case 1:
					Users.Admin(sc,conn,"Admin");
					break;
				case 2:
					Users.CustomerLogin(sc,conn,"Costumer");
					break;
				case 3:
					Users.CustomerSignup(sc,conn,"Costumer","Active");
					break;
				case 4:
					System.out.print("\nThankYou For Using Our Software .");
					System.exit(0);
					break;
					
				default :
					System.out.print("\nWrong Enter\n Please Try Again....!");
				 }
				 }catch(Exception e) {
				e.printStackTrace();
		}
		
	}
}
}