package com.demo.NextTrip.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NextTrip {
	@JsonProperty("DepartureText")
	String DepartureText;

	public String getDepartureText() {
		return DepartureText;
	}

	public void setDepartureText(String departureText) {
		DepartureText = departureText;
	}

	@Override
	public String toString() {
		return "nextTrip [DepartureText=" + DepartureText + "]";
	}

}
