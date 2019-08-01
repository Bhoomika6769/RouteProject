package com.demo.NextTrip.serviceImpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.NextTrip.client.ProviderManagementRestClient;
import com.demo.NextTrip.entities.NextTrip;
import com.demo.NextTrip.service.ProviderService;

@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	ProviderManagementRestClient partnerManagementRestClient;

	@Override
	public NextTrip[] getNextBus(String current, String direction, String destination)
			throws JSONException, IOException {
		String route1 = partnerManagementRestClient.getRoutes(current);

		String directions = partnerManagementRestClient.getDirections(route1, direction);

		String stop = partnerManagementRestClient.getStops(route1, directions, destination);

		return partnerManagementRestClient.getNextTrip(route1, directions, stop);

	}

}
