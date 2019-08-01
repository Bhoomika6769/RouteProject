package com.demo.NextTrip.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.NextTrip.entities.NextTrip;
import com.demo.NextTrip.service.ProviderService;

@RestController
public class Controller {

	@Autowired
	private ProviderService providerService;

	@RequestMapping(value = "/providers/{current}/{direction}/{destination}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String nextBus(@PathVariable String current, @PathVariable String destination,
			@PathVariable String direction) throws JSONException, IOException {

		NextTrip[] s = providerService.getNextBus(current, direction, destination);
		String time = s[0].getDepartureText();
		if (time.isEmpty()) {
			return "Last bus departed";
		}
		return time + " minutes";
	}

}
