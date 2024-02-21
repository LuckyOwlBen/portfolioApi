package com.benco.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.benco.portfolio.services.CalendarService;
import com.benco.portfolio.beans.requests.AppointmentRequest;
import com.benco.portfolio.beans.responses.AppointmentResponse;
import com.benco.portfolio.beans.responses.AvailabilityResponse;

@RestController
public class CalendarController {

	public CalendarController(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	private CalendarService calendarService;

	@PostMapping("/scheduleAppointment")
	public ResponseEntity<AppointmentResponse> scheduleAppointment(@RequestBody AppointmentRequest request) {
		return calendarService.scheduleAppointment(request);
	}

	@GetMapping("/checkAvailability")
	public ResponseEntity<AvailabilityResponse> checkAvailability(@RequestHeader("firstDate") String firstDate, @RequestHeader("secondDate") String secondDate ) {
		return calendarService.checkAvailabilityForDateRange(firstDate, secondDate);
	}
}
