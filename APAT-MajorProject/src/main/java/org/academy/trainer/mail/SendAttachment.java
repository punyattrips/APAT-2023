package org.academy.trainer.mail;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.academy.trainer.webUI.Wvf_Trainer_UI;

import javax.activation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class SendAttachment{
//	public static void main(String[] arg) throws IOException{
	public static void sendmail() throws IOException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		SimpleDateFormat Time =new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		
		String mailSubject= "WinVinaya || Academy || Trainer - Candidate Performance Monitor ";
		String mailBody= "Trainer - Candidate Performance Monitor.\n \n Data collected at: "+date.toString()+
				"\n \n Trainer ID: "+Wvf_Trainer_UI.loginID.getText().toString()+"\n \n Course Name: "+Wvf_Trainer_UI.courseName.getText().toString()+
				"\n \n Batch Name: "+Wvf_Trainer_UI.batchName.getText().toString()+"\n \n Co-Ordinate ID: "+Wvf_Trainer_UI.co_Ordinate_ID.getText().toString()+
				"\n \n Please find the attachment.";
		
		String testReportName= "Performance monitor "+formatter.format(date)+".xlsx";

		//		 Recipient's email ID needs to be mentioned.
		String to = Wvf_Trainer_UI.co_Ordinate_ID.getText().toString();
		String cc = Wvf_Trainer_UI.loginID.getText().toString();
				
//		// Sender's email ID needs to be mentioned
		 String from = "WVFacademy@gmail.com";

		 final String username = "WVFacademy@gmail.com";//change accordingly
			final String password = "ejcpxhepjejmzgkm";//change accordingly
			
		
		  // Sender's email ID needs to be mentioned
//	      String from = "prabakaruppaiyan2023@gmail.com";
//
//	      final String username = "prabakaruppaiyan2023@gmail.com";//change accordingly
//	      final String password = "mafabxwzcuattisp";//change accordingly
			
		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25"); 

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			InternetAddress[] iAdressArray = InternetAddress.parse(cc);
			message.setRecipients(Message.RecipientType.CC, iAdressArray);

			// Set Subject: header field
			message.setSubject(mailSubject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText(mailBody);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "C:\\Academy\\trainer\\Batch\\export\\Performance_monitor_report.xlsx";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(testReportName);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Mail Sent successfully to "+to+" with CC "+cc);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
