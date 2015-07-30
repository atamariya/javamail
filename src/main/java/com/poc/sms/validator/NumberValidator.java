package com.poc.sms.validator;

public class NumberValidator {

	/**
	 * Method for Basic Mobile Number Validation
	 */
	public boolean isValid(String number) {
		if (null == number)
			return false;

		return (number.length() > 10 && number.contains("+"));
	}
}
