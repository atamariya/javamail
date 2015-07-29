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




	public boolean sendMail(String to ) throws IOException {

		Boolean bool = false;
		
		final String from, username, password;
		EmailGetProperty mailProp=new EmailGetProperty();
		Properties props = mailProp.getProp();


		

			// reading values from config.property file
			from = props.getProperty("sender");
			username = props.getProperty("username");
			password = props.getProperty("password");
			

			System.out.println("reading SMTP server details from property file");
			
			
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
				message.setSubject("Testing Subject");

				// Now set the actual message

				

				message.setText("Hi Globytes, this is sample for to check send "

						+ "email using JavaMailAPI ");

				// Send message
				Transport.send(message);

				System.out.println("Sent message successfully....");

				bool = true;

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			return bool;
		} 
			


		} 
		

		// props.put("mail.smtp.debug", "true");
		
		


	public static void main(String[] args) throws IOException{
		// Recipient's email ID needs to be mentioned.
		Scanner in = new Scanner(System.in);
		System.out.println("Enter recipient mail id");
		String to = in.nextLine();
		SendEmail mail = new SendEmail();

		try
		{
			mail.sendMail(to);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		
		mail.sendMail(to);
		

		

		// Sender's email ID needs to be mentioned

	}
}
