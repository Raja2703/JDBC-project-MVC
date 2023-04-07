package com.maths.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	static Scanner in = new Scanner(System.in);
	static Connection con;
	static PreparedStatement st;
	static ResultSet rs;
	
	public static void main(String[] args) throws SQLException {
		
		try {
			// Database connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee_details","root","Root@2709");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.print("Enter your choice: ");
			int choice=in.nextInt();
			
			switch(choice) {
				case 1:
					
					System.out.println("\nSign up");
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
					if(ValidityChecker.isUnameValid(userName)) {
						if(ValidityChecker.isPassValid(password)) {
							if(ValidityChecker.isEmailValid(email)) {
								if(ValidityChecker.isDobValid(dob)) {
									rg.register();
								}
							}
						}
					}
					break;
					
				case 2:
					boolean isLogged = false;
					int loginFailCount = 0;
					String resetChoice = null;
					userName = in.nextLine();
					while(!isLogged) {
						
						System.out.println("\nLogin");
						System.out.print("Enter username:");
						userName = in.nextLine();
						userName = userName.toLowerCase();
						System.out.print("Enter password:");
						password = in.nextLine();
						
						Login lg = new Login(userName,password);
						if(ValidityChecker.isUnameValid(userName)) {
							if(ValidityChecker.isPassValid(password)) {
								isLogged = lg.login();
								if(!isLogged) {
									loginFailCount++;
								}
								if(loginFailCount >= 2) {
									System.out.print("\nDo you want to reset password(y/n): ");
									resetChoice = in.nextLine();
									if(resetChoice.equals("y")) {
										PrepareEmail mail = new PrepareEmail(userName);
										mail.mail();
										loginFailCount = 0;
									}
								}
							}
						}
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
