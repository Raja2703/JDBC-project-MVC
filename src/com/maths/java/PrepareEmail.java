package com.maths.java;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PrepareEmail extends Main {
	
	static String uname;
	String from = "srajasep27@gmail.com";
	String appPassword = "qmmfftcygokcrimt"; // app password

	public PrepareEmail(String uname) {
		PrepareEmail.uname = uname;
	}

	public boolean mail() {
		String smtpHostServer = "smtp.gmail.com";
		try {
			String query = "select email from user_cred where uname=?";
			st = con.prepareStatement(query);
			st.setString(1, uname);
			rs = st.executeQuery();
			String toEmail = null;
			while(rs.next()) {
				toEmail = rs.getString(1);
			}

			Properties props = System.getProperties();
			props.put("mail.smtp.host", smtpHostServer);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, appPassword);
				}
			});

			int min = 2000;
			int max = 9000;
			int OTP = min + (int) (Math.random() * ((max - min) + 1));

			boolean isPasswordChanged= sendEmail(session, toEmail, "OTP verification", OTP);
			if(isPasswordChanged)
				return true;
		}catch(Exception e) {
			System.out.println(e);
		}
		return false;
		
	}

	public boolean sendEmail(Session session, String toEmail, String subject, int OTP) {
		try {
			MimeMessage msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(from));
			msg.setSubject(subject);
			msg.setText("Enter this OTP to generate new password\n\n OTP = " + OTP);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("\nMessage is ready");
			Transport.send(msg);
			System.out.println("Email sent successfully");

			ResetPassword reset = new ResetPassword(OTP, uname);
			boolean isPasswordChanged = reset.resetPass();
			if(isPasswordChanged)
				return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
