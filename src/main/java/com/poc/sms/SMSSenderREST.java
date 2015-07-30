package com.poc.sms;

import org.apache.log4j.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

//Using Site2SMS features to send an SMS 
public class SMSSenderREST {

	private static final Logger LOGGER = Logger.getLogger(SMSSenderREST.class);

	// The template form of the end-point
	private final String restGetTemplate = "https://site2sms.p.mashape.com/index.php?pwd=%s&uid=%s&msg=%s&phone=%s";

	/**
	 * Method for actually sending the message using a REST endpoint.Accepts the
	 * mobile number of the receipient and the actual text content
	 */
	public boolean sendMessage(String text, String receipient) {

		boolean returnvalue = false;

		try {

			// Unirest is a very intutive and easy to use REST client
			// Making the rest call
			HttpResponse<JsonNode> response = Unirest
					.get(format(text, receipient))
					.header("X-Mashape-Key",
							"laSbgihXsTmshiRMedXp59UBLpEjp1ZF9AvjsneJLni7iv2S4P")
					.header("Accept", "application/json").asJson();

			LOGGER.info("JSON response:::" + response.getBody().toString());
		} catch (UnirestException e) {
			LOGGER.error("Error Occured while making a rest call");
		}
		return returnvalue;
	}

	//
	/**
	 * Method to format the string,basic utility
	 */
	private String format(String text, String receipient) {
		return String.format(restGetTemplate, "sachin100", "9945800955", text,
				receipient);
	}
}
