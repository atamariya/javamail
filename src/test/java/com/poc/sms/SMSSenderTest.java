package com.poc.sms;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.smslib.SMSLibException;
import org.smslib.TimeoutException;
import static org.junit.Assert.*;

public class SMSSenderTest {

	private SMSSender smsSender;

	@Before
	public void setUp() throws Exception {
		smsSender = new SMSSender();
	}
	
	@Test
	public void testSendMessage() throws TimeoutException, SMSLibException, IOException, InterruptedException{
		
		//Testing for negative case
		boolean status=smsSender.sendMessage("919945800955", "Hi from SMSLib........!!!! Working for now");
		assertEquals(status, false);
		
		//Testing for positive case
		status=smsSender.sendMessage("+919945800955", "Hi from SMSLib........!!!! Working for now");
		assertEquals(status, true);
	}
	
}
