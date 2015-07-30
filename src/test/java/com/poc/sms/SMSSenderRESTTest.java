package com.poc.sms;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SMSSenderRESTTest {

	private SMSSenderREST senderRest;

	@Before
	public void setUp() throws Exception {
		senderRest = new SMSSenderREST();
	}

	@Test
	public void testSendMessage() {
		boolean status = senderRest.sendMessage("Howdy!!!", "9945800955");
		assertEquals(false, status);
	}
}
