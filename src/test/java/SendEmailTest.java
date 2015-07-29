import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SendEmailTest {
	
	SendEmail mail;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mail=new SendEmail();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendMail() {
		
		Boolean bool=false;
		try
		{
			bool=mail.sendMail("amrit.adheesh@globallogic.com");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		assertEquals(true,bool);
	}

}
