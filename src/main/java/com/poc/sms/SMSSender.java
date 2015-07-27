package com.poc.sms;
import java.io.IOException;

import org.smslib.GatewayException;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.http.BulkSmsHTTPGateway;

public class SMSSender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Example: Send message from BulkSMS HTTP Interface.");
			System.out.println(Library.getLibraryDescription());
			System.out.println("Version: " + Library.getLibraryVersion());
		
		BulkSmsHTTPGateway bulkSmsHTTPGateway=new BulkSmsHTTPGateway("bulksms.http.1", "a_chandrasekaran", "sachin100");
		bulkSmsHTTPGateway.setOutbound(true);
		System.out.println("Outbound message count::"+bulkSmsHTTPGateway.getOutboundMessageCount());
		System.out.println(bulkSmsHTTPGateway.getAttributes());
		Service.getInstance().addGateway(bulkSmsHTTPGateway);
		Service.getInstance().startService();

		OutboundMessage outboundMessage=new OutboundMessage("+919945800955", "Hello From SMSLib");
		//bulkSmsHTTPGateway.sendMessage(outboundMessage);
		Service.getInstance().startService();
		System.out.println("Remaining credit: " + bulkSmsHTTPGateway.queryBalance());
		Service.getInstance().sendMessage(outboundMessage);
		
		System.out.println("Message might get sent");
		System.out.println("Now Sleeping - Hit <enter> to terminate.");
		System.in.read();
		Service.getInstance().stopService();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SMSLibException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
