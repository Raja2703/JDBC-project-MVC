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
	public void mail() {
		String smtpHostServer = "smtp.gmail.com";
		System.out.println("Enter email to send otp: ");
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
		
		sendEmail(session, toEmail, "otp","otp = 1214");
	}
	
	public void sendEmail(Session session,String toEmail,String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			//set message headers
		    msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		    msg.addHeader("format", "flowed");
		    msg.addHeader("Content-Transfer-Encoding", "8bit");
		    
		    msg.setFrom(new InternetAddress("srajasep27@gmail.com"));
//		    msg.setReplyTo(InternetAddress.parse("srajasep27@gmail.com",false));
		    msg.setSubject(subject);
		    msg.setText(body);
		    msg.setSentDate(new Date());
		    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail,false));
		    System.out.println("Message is ready");
		    Transport.send(msg);
		    System.out.println("Email sent successfully");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
