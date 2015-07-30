package com.poc.sms.validator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NumberValidatorTest {

	private NumberValidator numberValidator;

	@Before
	public void setUp() throws Exception {
		numberValidator = new NumberValidator();
	}

	@Test
	public void testisValid() {
		assertEquals(numberValidator.isValid("+919945800955"), true);
		assertEquals(numberValidator.isValid("9870"), false);
		assertEquals(numberValidator.isValid("919945800955"), false);
	}
}
