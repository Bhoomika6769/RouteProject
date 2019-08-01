package com.demo.NextTrip.client;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import com.demo.NextTrip.entities.Directions;
import com.demo.NextTrip.entities.NextTrip;
import com.demo.NextTrip.entities.Providers;
import com.demo.NextTrip.entities.Routes;
import com.demo.NextTrip.entities.Stops;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProviderManagementRestClient extends BaseClient<String> {

	@Autowired
	private ObjectMapper jsonObjectMapper;

	public Providers[] getPartners() throws IOException {

		String url = "http://svc.metrotransit.org/nextrip/providers";
		String response = getRequestForResponse(url, String.class);
		return serializeJsonAsProviders(response);

	}

	public String getRoutes(String current) throws JSONException, IOException {
		String urlRoutes = "http://svc.metrotransit.org/NexTrip/Routes";
		String response = getRequestForResponse(urlRoutes, String.class);
		Routes[] routes = serializeJsonAsRoutes(response);
		for (int i = 0; i < routes.length; i++) {
			String description = routes[i].getDescription();
			if (description.contains(current)) {
				return routes[i].getRoute();
			}
		}
		return "not found";

	}

	public String getDirections(String route, String direction) throws JSONException, IOException {
		String urlDirections = "http://svc.metrotransit.org/NexTrip/Directions/" + route;

		System.out.println(urlDirections);

		String response = getRequestForResponse(urlDirections, String.class);
		Directions[] directions = serializeJsonAsDirections(response);
		for (int i = 0; i < directions.length; i++) {

			String text = directions[i].getText();
			if (text.contains(direction)) {
				return directions[i].getValue();
			}
		}
		return "not found";

	}

	public String getStops(String route, String directions, String destination) throws JSONException, IOException {
		String urlStops = "http://svc.metrotransit.org/NexTrip/Stops/" + route + "/" + directions;

		System.out.println(urlStops);

		String response = getRequestForResponse(urlStops, String.class);
		Stops[] stops = serializeJsonAsStops(response);
		for (int i = 0; i < stops.length; i++) {

			String text = stops[i].getText();
			if (text.contains(destination)) {
				return stops[i].getValue();
			}
		}
		return "not found";

	}

	public NextTrip[] getNextTrip(String route, String directions, String stop) throws IOException {
		String urlStops = "http://svc.metrotransit.org/NexTrip/" + route + "/" + directions + "/" + stop;
		String response = getRequestForResponse(urlStops, String.class);
		NextTrip[] nextTrip = serializeJsonAsNextTrip(response);
		return nextTrip;
	}

	private NextTrip[] serializeJsonAsNextTrip(String json)
			throws JsonParseException, JsonMappingException, IOException {
		return jsonObjectMapper.readValue(json, NextTrip[].class);
	}

	private Stops[] serializeJsonAsStops(String json) throws JsonParseException, JsonMappingException, IOException {
		return jsonObjectMapper.readValue(json, Stops[].class);
	}

	private Providers[] serializeJsonAsProviders(String json)
			throws JsonParseException, JsonMappingException, IOException {
		return jsonObjectMapper.readValue(json, Providers[].class);
	}

	private Routes[] serializeJsonAsRoutes(String json) throws JsonParseException, JsonMappingException, IOException {
		return jsonObjectMapper.readValue(json, Routes[].class);
	}

	private Directions[] serializeJsonAsDirections(String json)
			throws JsonParseException, JsonMappingException, IOException {
		return jsonObjectMapper.readValue(json, Directions[].class);
	}

}