package com.poc.sms;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.http.BulkSmsHTTPGateway;

import com.poc.sms.validator.NumberValidator;

public class SMSSender {

	/**
	 * @param args
	 */

	private static final Logger LOGGER = Logger.getLogger(SMSSender.class);

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// try {
	// System.out.println("Example: Send message from BulkSMS HTTP Interface.");
	// System.out.println(Library.getLibraryDescription());
	// System.out.println("Version: " + Library.getLibraryVersion());
	//
	// BulkSmsHTTPGateway bulkSmsHTTPGateway=new
	// BulkSmsHTTPGateway("bulksms.http.1", "a_chandrasekaran", "sachin100");
	// bulkSmsHTTPGateway.setOutbound(true);
	//
	// Service.getInstance().addGateway(bulkSmsHTTPGateway);
	// //Service.getInstance().startService();
	//
	// OutboundMessage outboundMessage=new OutboundMessage("+919945800955",
	// "Hello From SMSLib");
	// //bulkSmsHTTPGateway.sendMessage(outboundMessage);
	// Service.getInstance().startService();
	// System.out.println("Remaining credit: " +
	// bulkSmsHTTPGateway.queryBalance());
	// Service.getInstance().sendMessage(outboundMessage);
	//
	// System.out.println("Now Sleeping - Hit <enter> to terminate.");
	// System.in.read();
	// Service.getInstance().stopService();
	// } catch (TimeoutException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (GatewayException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (SMSLibException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	
	public boolean sendMessage(String number, String text)
			throws TimeoutException, SMSLibException, IOException,
			InterruptedException {
		boolean messageStatus = true;
		LOGGER.info(Service.getInstance().getServiceStatus());
		Service.getInstance().addGateway(buildGateway());
		boolean isNumberValid=new NumberValidator().isValid(number);
		LOGGER.info("Validity of the number::"+isNumberValid);
		//Service.getInstance().startService();
		OutboundMessage outboundMessage = null;
		if (isNumberValid) {
			LOGGER.info("Message might get sent");
			outboundMessage = createMessage(number, text);
			Service.getInstance().startService();
			LOGGER.info(Service.getInstance().getServiceStatus());
			LOGGER.info("Gateways::"+Service.getInstance().getGateways().toString());
			//Service.getInstance().sendMessage(outboundMessage);
		} else {
			messageStatus = false;
		}
		return messageStatus;
	}

	public AGateway buildGateway() {
		// Ideally the password ought to be read from a properties file
		BulkSmsHTTPGateway bulkSmsHTTPGateway = new BulkSmsHTTPGateway(
				"bulksms.http.1", "a_chandrasekaran", "sachin100");
		bulkSmsHTTPGateway.setOutbound(true);
		try {
			LOGGER.info("Gateway Balance::"+bulkSmsHTTPGateway.queryBalance());
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
		}
		return bulkSmsHTTPGateway;
	}

	public OutboundMessage createMessage(String recipientNumber, String text) {
		return new OutboundMessage(recipientNumber, text);

	}

}
