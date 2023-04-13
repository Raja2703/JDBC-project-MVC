package com.maths.java;

import java.sql.SQLException;

public class ResetPassword extends Main {

	static int OTP;
	static String uname;
	static String newEncryptedPass;

	public ResetPassword(int OTP, String uname) {
		ResetPassword.OTP = OTP;
		ResetPassword.uname = uname;
	}

	public static boolean resetPass() throws SQLException {
		int failCount = 0;
		int chance = 3;
		while (failCount < 3) {
			System.out.print("\nEnter OTP: ");
			int enteredOtp = in.nextInt();
			if (enteredOtp == OTP) {
				try {
					String newPassword = in.nextLine();
					System.out.print("\nEnter new password: ");
					newPassword = in.nextLine();
					if (ValidityChecker.isPassValid(newPassword)) {
						newEncryptedPass = Encryption.getMD5(newPassword);
						String query = "update user_cred set pass=? where uname=?";
						st = con.prepareStatement(query);
						st.setString(1, newEncryptedPass);
						st.setString(2, uname);
						int count = st.executeUpdate();
						System.out.println("Password changed successfully\n");
						return true;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				chance--;
				if(chance!=0)
					System.out.println("Wrong OTP entered. Please try again!");
				else
					System.out.println("Wrong OTP entered");
				if(chance!=1)
					System.out.println("You have "+chance+" chances left");
				else {
					System.out.println("You have "+chance+" chance left");
//					return false;
				}
				failCount++;
				
			}
		}
		return false;
	}
}
