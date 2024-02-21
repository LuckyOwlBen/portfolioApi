package com.benco.portfolio.beans.responses;

import java.util.List;

import com.benco.portfolio.beans.CalendarEntry;

public class AvailabilityResponse {

	public AvailabilityResponse(List<CalendarEntry> calendarEntries) {
		this.calendarEntries = calendarEntries;
	}

	private List<CalendarEntry> calendarEntries;

	public List<CalendarEntry> getCalendarEntries() {
		return calendarEntries;
	}

	public void setCalendarEntries(List<CalendarEntry> calendarEntries) {
		this.calendarEntries = calendarEntries;
	}

}
