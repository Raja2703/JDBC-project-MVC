package com.maths.java;

import java.util.regex.Pattern;

public class Login extends Main{
	static String uname = null;
	static String pass = null;
	
	public Login(String uname,String pass) {
		Login.uname = uname;
		Login.pass = pass;
	}
	
	public boolean isValid() {
		String pattern1 = "[a-z0-9_]{5,12}";                   // pattern for username
		String pattern2 = "[a-zA-Z0-9@!$%#&]{7,15}";           // pattern for password
		if(Pattern.matches(pattern1,uname) & Pattern.matches(pattern2,pass)) {
			return true;
		}else {
			System.out.println("username or password did not meet the requirements");
			return false;
		}
	}
	
	public static void login() {
		String query = "select * from user_cred where uname=? and pass=?";
		try {
			st = con.prepareStatement(query);
			rs = st.executeQuery();
			
			if(rs.next()) {
				System.out.println("Logged in");
			}else {
				System.out.println("invalid username or password");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
