package com.benco.portfolio.beans;

import java.util.Map;

public class CalendarEntry {

	private String date;

	private Map<Integer, Integer> availabilityMap;

	public CalendarEntry(String date, Map<Integer, Integer> availabilityBitMap) {
		this.date = date;
		this.availabilityMap = availabilityBitMap;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<Integer, Integer> getAvailabilityMap() {
		return availabilityMap;
	}

	public void setAvailabilityMap(Map<Integer, Integer> availabilityMap) {
		this.availabilityMap = availabilityMap;
	}
	
}
