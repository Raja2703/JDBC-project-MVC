package com.maths.java;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Register extends Main{
	static String uname = null;
	static String pass = null;
	static String email = null;
	static String dob= null;
	
	public Register(String uname,String pass,String email,String dob) {
		Register.uname = uname;
		Register.pass = pass;
		Register.email = email;
		Register.dob = dob;
	}
	
	public static void register() throws SQLException {
		
		String query = "insert into user_cred values(?,?,?,?)";
		try {
			st = con.prepareStatement(query);
			String encryptedPass = Encryption.getMD5(pass);
			st.setString(1, uname);
			st.setString(2, encryptedPass);
			st.setString(3, email);
			st.setString(4, dob);
			int count = st.executeUpdate();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
