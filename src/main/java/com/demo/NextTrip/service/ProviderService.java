package com.demo.NextTrip.service;

import java.io.IOException;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import com.demo.NextTrip.entities.NextTrip;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public interface ProviderService {

	public NextTrip[] getNextBus(String current, String direction, String destination)
			throws JsonProcessingException, JSONException, IOException;

}
