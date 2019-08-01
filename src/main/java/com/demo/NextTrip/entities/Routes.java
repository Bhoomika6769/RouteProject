package com.demo.NextTrip.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Routes {

	@JsonProperty("Description")
	String Description;

	@JsonProperty("ProviderID")
	String ProviderID;

	@JsonProperty("Route")
	String Route;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getProviderID() {
		return ProviderID;
	}

	public void setProviderID(String providerID) {
		ProviderID = providerID;
	}

	public String getRoute() {
		return Route;
	}

	public void setRoute(String route) {
		Route = route;
	}

	@Override
	public String toString() {
		return "Routes [Description=" + Description + ", ProviderID=" + ProviderID + ", Route=" + Route + "]";
	}

}
