package com.demo.NextTrip.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class BaseClient<V> {

	@Autowired
	private RestTemplate restTemplate;
	

	V getRequestForResponse(String endpoint, Class<V> responseType) throws JsonProcessingException {

		ResponseEntity<V> response = restTemplate.getForEntity(endpoint, responseType);

		return response.getBody();

	}

}
