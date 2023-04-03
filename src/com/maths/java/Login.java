package com.maths.java;

public class Login extends Main{
	static String uname = null;
	static String pass = null;
	
	public Login(String uname,String pass) {
		Login.uname = uname;
		Login.pass = pass;
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
