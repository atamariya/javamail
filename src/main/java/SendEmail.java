
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public boolean sendMail(String to) {
		
		
		Boolean bool = false;
		InputStream input=null;
		final String from, username, password, host, port;
		Properties props = new Properties();

		try {
			
			//loading config.property file
			input = new FileInputStream("src/main/resources/config.properties");
			props.load(input);
			
			//reading values from config file
			from = props.getProperty("sender");
			username = props.getProperty("username");
			password = props.getProperty("password");
			host = props.getProperty("host");
			port = props.getProperty("port");
			
			//other smtp properties set up
			
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
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

				// Set Subject: header field
				message.setSubject("Testing Subject");

				// Now set the actual message
				message.setText("Hi, this is sample for to check send "
						+ "email using JavaMailAPI ");

				// Send message
				Transport.send(message);

				System.out.println("Sent message successfully....");
				
				bool=true;

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// props.put("mail.smtp.debug", "true");

		// Get the Session object.
		return bool;
		
	}

	public static void main(String[] args) {
		// Recipient's email ID needs to be mentioned.
		Scanner in = new Scanner(System.in);
		System.out.println("Enter recipient mail id");
		String to = in.nextLine();
		SendEmail mail = new SendEmail();
		mail.sendMail(to);

		// Sender's email ID needs to be mentioned

	}
}
