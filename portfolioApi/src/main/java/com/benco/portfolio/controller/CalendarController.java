package com.benco.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.benco.portfolio.services.CalendarService;
import com.benco.portfolio.beans.requests.AppointmentRequest;
import com.benco.portfolio.beans.responses.AppointmentResponse;

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
}
