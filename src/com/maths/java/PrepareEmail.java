package com.maths.java;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class PrepareEmail extends Main{
	static String uname;
	
	public PrepareEmail(String uname) {
		PrepareEmail.uname = uname;
	}
	
	public void mail() {
		String smtpHostServer = "smtp.gmail.com";
		System.out.print("Enter email to send otp: ");
		String toEmail = in.nextLine();
		
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHostServer);
		props.put("mail.smtp.starttls.enable", true); 
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "587");    
	    
	    String from = "srajasep27@gmail.com";
	    String appPassword = "qmmfftcygokcrimt"; //app password
	    Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {  
	       return new PasswordAuthentication(from,appPassword);  
	   }  
	   });
	    
	    int min = 2000;
	    int max = 9000;
	    int OTP = min + (int)(Math.random() * ((max-min)+1));
		
		sendEmail(session, toEmail, "OTP verification",OTP);
	}
	
	public void sendEmail(Session session,String toEmail,String subject, int OTP) {
		try {
			MimeMessage msg = new MimeMessage(session);
			//set message headers
		    msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		    msg.addHeader("format", "flowed");
		    msg.addHeader("Content-Transfer-Encoding", "8bit");
		    
		    msg.setFrom(new InternetAddress("srajasep27@gmail.com"));
		    msg.setSubject(subject);
		    msg.setText("Enter this OTP to generate new password\n\n OTP = "+OTP);
		    msg.setSentDate(new Date());
		    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail,false));
		    System.out.println("\nMessage is ready");
		    Transport.send(msg);
		    System.out.println("Email sent successfully");
		    
		    ResetPassword reset = new ResetPassword(OTP,uname);
		    reset.resetPass();
		    
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
