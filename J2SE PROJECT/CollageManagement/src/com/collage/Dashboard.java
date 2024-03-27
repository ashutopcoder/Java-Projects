package com.collage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Dashboard {
	private static Connection conn ;
	private static Scanner sc=new Scanner(System.in);
	public static void main(String args[]) {
		try {
			conn =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/school","root","2001");
			while(true) {
			System.out.println("Press 1 To Add Student : ");
			System.out.println("Press 2 To Add Teacher : ");
			System.out.println("Press 3 To View Student : ");
			System.out.println("Press 4 View Teacher : ");
			System.out.println("Press 5 To Update Result : ");
			System.out.println("Press 6 To View Result : ");
			System.out.println("Press 7 To Exit : ");
			System.out.println("\nEnter Your Choice : ");
			int choice =sc.nextInt();
			switch(choice) {
			case 1:
				Users.AddUsers(sc, conn, "Student");
				System.out.println("________________________________");
				break;
			case 2: 
				Users.AddUsers(sc, conn, "Teacher");
				System.out.println("________________________________");
				break;
			case 3:
				Users.ViewUser(conn,"Student");
				System.out.println("_______________________________");
				break;
			case 4:
				Users.ViewUser(conn,"Teacher");
				System.out.println("________________________________");
				break;
			case 5:
				System.out.println("Enter Id : ");
					int id =sc.nextInt();
						if(Users.CheckStudent(id, conn))
							Users.UpdateResult(sc,conn,id);
						else
							System.out.println("Student Not Found .");
							System.out.println("__________________________");
				break;
			case 6:
				System.out.println("Enter Id : ");
				id =sc.nextInt();
				if(Users.CheckStudent(id, conn))
					Users.ViewResult(conn,id);
				else
					System.out.println("Student Not Found .");
					System.out.println("__________________________");
				
				break;
			case 7:
				System.out.println("BYE-BYE !");
				System.exit(0);
				break;
			default:
					System.out.println("Wrong Entered!\nPlease Try Again.");
					
			}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
