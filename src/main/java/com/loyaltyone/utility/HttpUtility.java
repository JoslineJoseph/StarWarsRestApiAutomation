package com.loyaltyone.utility;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpUtility {
	
	public static HttpResponse sendGet(String url) {

		LoggerUtility log = new LoggerUtility("");
		log.info("Starting Http Get Request for "+ url);
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		HttpResponse response;
		try {
			response = client.execute(request);
			log.info("Completed Http Get Request");
			log.info("Response Status "+ response.getStatusLine().getStatusCode());
			return response;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
