package com.maths.java;

public class Login extends Main {
	static String uname;
	static String pass;

	public Login(String uname, String pass) {
		Login.uname = uname;
		Login.pass = pass;
	}

	public boolean login() {
		String encryptedPass = Encryption.getMD5(pass);
		String query = "select * from user_cred where uname=? and pass=?";
		try {
			st = con.prepareStatement(query);
			st.setString(1, uname);
			st.setString(2, encryptedPass);
			rs = st.executeQuery();

			if (rs.next()) {
				System.out.println("Logged in!!!");
				return true;
			} else {
				System.out.println("invalid username or password");
//				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
