package com.poc.sms;

import org.apache.log4j.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SMSSenderREST {

	private static final Logger LOGGER = Logger.getLogger(SMSSender.class);
	
	private final String restGetTemplate="https://site2sms.p.mashape.com/index.php?pwd=%s&uid=%s&msg=%s&phone=%s";
	
	public boolean sendMessage(String text, String receipient) {

		boolean returnvalue = false;
		
		try {
			HttpResponse<JsonNode> response = Unirest.get(format(text, receipient))
			.header("X-Mashape-Key", "laSbgihXsTmshiRMedXp59UBLpEjp1ZF9AvjsneJLni7iv2S4P")
			.header("Accept", "application/json")
			.asJson();
			
			LOGGER.info("JSON response:::"+response.getBody().toString());
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error Occured while making a rest call");
		}
		return returnvalue;
	}
	
	private String format(String text,String receipient)
	{
		return String.format(restGetTemplate, "sachin100","9945800955",text,receipient);
	}
}
