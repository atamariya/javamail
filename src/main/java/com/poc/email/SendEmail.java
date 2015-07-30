package com.poc.email;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendEmail {




	public boolean sendMail(String to,String subject,String msg) throws IOException {
		

		Boolean bool = false;
		
		final String from, username, password;
		EmailGetProperty mailProp=new EmailGetProperty();
		Properties props = mailProp.getProp();
		Logger logger=Logger.getLogger(SendEmail.class.getName());

		

			// reading values from config.property file
		    logger.info("reading SMTP server details from property file");
			from = props.getProperty("sender");
			username = props.getProperty("username");
			password = props.getProperty("password");
			

			
			
			
			//System.out.println(props);
			


			// Get the Session object.
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
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

				// Set Subject: header field
				
				message.setSubject(subject);

				// Now set the actual message

				
				
				message.setText(msg);

				// Send message
				Transport.send(message);

				logger.info("Sent message successfully....");

				bool = true;

			} catch (MessagingException e) {
				logger.severe("Not able to create a message");
			}
			return bool;
		} 
			


		
		

		// props.put("mail.smtp.debug", "true");
		
		


	public static void main(String[] args) throws IOException{
		// Recipient's email ID needs to be mentioned.
		Scanner in = new Scanner(System.in);
		System.out.println("Enter recipient mail id");
		String to = in.nextLine();
		SendEmail mail = new SendEmail();
		String sub="Test mail";
		String msg="Hi Globytes,"+'\n'+'\n'+'\n'+ "this is sample for to check send "

						+ "email using JavaMailAPI. "+'\n'+'\n'+'\n'+'\n'+"Thanks & Regrads"+'\n'+'\n'+"Amrit Adheesh";

		/*try
		{
			mail.sendMail(to,sub,msg);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}*/

		
		mail.sendMail(to,sub,msg);
		in.close();
		

		

		// Sender's email ID needs to be mentioned

	}
}
