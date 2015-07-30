package com.poc.sms;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.http.BulkSmsHTTPGateway;

import com.poc.email.EmailGetProperty;
import com.poc.sms.validator.NumberValidator;

public class SMSSender {

	private static final Logger LOGGER = Logger.getLogger(SMSSender.class);

	/**
	 * Method for actually sending the message 
	 * Accepts the mobile number of the receipient and the actual text content
	 */
	public boolean sendMessage(String number, String text)
			throws TimeoutException, SMSLibException, IOException,
			InterruptedException {
		boolean messageStatus = true;
		LOGGER.info(Service.getInstance().getServiceStatus());
		Service.getInstance().addGateway(buildGateway());
		boolean isNumberValid = new NumberValidator().isValid(number);
		LOGGER.info("Validity of the number::" + isNumberValid);

		OutboundMessage outboundMessage = null;
		if (isNumberValid) {
			LOGGER.info("Message might get sent");
			outboundMessage = createMessage(number, text);
			Service.getInstance().startService();
			LOGGER.info(Service.getInstance().getServiceStatus());
			LOGGER.info("Gateways::"
					+ Service.getInstance().getGateways().toString());
			Service.getInstance().sendMessage(outboundMessage);
		} else {
			messageStatus = false;
		}
		return messageStatus;
	}

	/**
	 * Builds the BulkSMS gateway
	 * @throws IOException 
	 */
	private AGateway buildGateway() throws IOException {
		EmailGetProperty mailProp=new EmailGetProperty();
		Properties props = mailProp.getProp();
		BulkSmsHTTPGateway bulkSmsHTTPGateway = new BulkSmsHTTPGateway(
				"bulksms.http.1", props.getProperty("bulk2sms.uname"), props.getProperty("bulk2sms.password"));
		bulkSmsHTTPGateway.setOutbound(true);
		return bulkSmsHTTPGateway;
	}

	/**
	 * Message creation using SMSLib
	 */
	private OutboundMessage createMessage(String recipientNumber, String text) {
		return new OutboundMessage(recipientNumber, text);

	}

}
