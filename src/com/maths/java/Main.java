package com.maths.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

abstract public class Main {
	static Scanner in = new Scanner(System.in);
	static Connection con = null;
	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	abstract public boolean isValid();
	
	public static void main(String[] args) throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee_details","root","Root@2709");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.print("Enter your choice: ");
			int choice=in.nextInt();
			
			switch(choice) {
				case 1:
					String userName = in.nextLine();
					System.out.print("Enter username:");
					userName = in.nextLine();
					userName = userName.toLowerCase();
					System.out.print("Enter password:");
					String password = in.nextLine();
					System.out.print("Enter email:");
					String email = in.nextLine();
					System.out.print("Enter dob:");
					String dob = in.nextLine();
					
					Register rg = new Register(userName,password,email,dob);
					if(rg.isValid()) {
						System.out.println("valid");
						rg.register();
					}else {
						System.out.println("invalid");
					}
					break;
					
				case 2:
//					userName = in.nextLine();
					System.out.print("Enter username:");
					userName = in.nextLine();
					userName = userName.toLowerCase();
					System.out.print("Enter password:");
					password = in.nextLine();
					
					Login lg = new Login(userName,password);
					if(lg.isValid()) {
						System.out.println("valid");
						lg.login();
					}else {
						System.out.println("Invalid username or password");
					}
					break;
				default:
					System.out.println("Enter a valid choice");
					break;
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			con.close();
		}
		
		
	}

}