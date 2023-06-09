package com.maths.java;

import java.sql.SQLException;

public class Register extends Main {
	static String uname;
	static String pass;
	static String email;
	static String dob;

	public Register(String uname, String pass, String email, String dob) {
		Register.uname = uname;
		Register.pass = pass;
		Register.email = email;
		Register.dob = dob;
	}

	public void register() throws SQLException {

		String query = "insert into user_cred values(?,?,?,?)";
		try {
			st = con.prepareStatement(query);
			String encryptedPass = Encryption.getMD5(pass);
			st.setString(1, uname);
			st.setString(2, encryptedPass);
			st.setString(3, email);
			st.setString(4, dob);
			int count = st.executeUpdate();
			System.out.println("Registered successfullly");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
