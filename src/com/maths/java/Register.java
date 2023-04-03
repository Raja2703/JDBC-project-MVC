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
	
	public static boolean isValid() {
		String pattern1 = "[a-z0-9_]{5,12}"; // pattern for user name
		String pattern2 = "[a-zA-Z0-9@!$%#&]{7,15}"; // pattern for user name
		if(Pattern.matches(pattern1,uname) & Pattern.matches(pattern2,pass)) {
			return true;
		}else {
			System.out.println("username or password did not meet the requirements");
			return false;
		}
	}
	
	public static String getMD5(String pass) {
		String encryptedValue = null;
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte messagedigest[]=md.digest(pass.getBytes());
			BigInteger bigInt = new BigInteger(1,messagedigest);
			encryptedValue = bigInt.toString(16);
			return encryptedValue;
		}catch(NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static void register() throws SQLException {
		
		String query = "insert into user_cred values(?,?,?,?)";
		try {
			st = con.prepareStatement(query);
			String encryptedPass = getMD5(pass);
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
