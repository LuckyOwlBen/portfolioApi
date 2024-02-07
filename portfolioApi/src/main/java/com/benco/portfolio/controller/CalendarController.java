package com.benco.portfolio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.benco.portfolio.repositories.CalendarRepository;
import com.benco.portfolio.entities.CalendarEntity;
import com.benco.portfolio.beans.requests.AppointmentRequest;

@RestController
public class CalendarController {

	@Autowired
	public CalendarController(CalendarRepository repository) {
		this.repository = repository;
	}

	CalendarRepository repository;

	@PostMapping("/scheduleAppointment")
	public String scheduleAppointment(@RequestHeader HttpHeaders headers, AppointmentRequest request) {
		CalendarEntity calendarEntity = new CalendarEntity();
		calendarEntity.setDate(LocalDate.now());
		repository.save(calendarEntity);
		return "success";
	}
}
