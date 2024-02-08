package com.benco.portfolio.services;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.benco.portfolio.beans.requests.AppointmentRequest;
import com.benco.portfolio.beans.responses.AppointmentResponse;
import com.benco.portfolio.repositories.CalendarRepository;
import com.benco.portfolio.repositories.CustomerRepository;
import com.benco.portfolio.entities.CalendarEntity;
import com.benco.portfolio.entities.CustomerEntity;

@Service
public class CalendarService {

	public CalendarService(CalendarRepository calendarRepository,
			CustomerRepository customerRepository) {
		this.calendarRepository = calendarRepository;
		this.customerRepository = customerRepository;
	}

	private CalendarRepository calendarRepository;
	private CustomerRepository customerRepository;

	public ResponseEntity<AppointmentResponse> scheduleAppointment(AppointmentRequest request) {
		CustomerEntity customerEntity = findCustomerEntity(request);
		CalendarEntity calendarEntity = findOrCreateCalendarEntity(request);
		HttpStatus status = HttpStatus.CONFLICT;
		boolean booked = false;
		if(calendarEntity.checkAvailability(request.getTimeslot())) {
			booked = calendarEntity.bookAppointment(customerEntity, request.getTimeslot());
			calendarRepository.save(calendarEntity);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(new AppointmentResponse(booked), status);
	}

	private CalendarEntity findOrCreateCalendarEntity(AppointmentRequest request) {
		CalendarEntity calendarEntity = calendarRepository.findByDate(
				LocalDate.of(request.getYear(), request.getMonth(), request.getDay()));
		 return calendarEntity == null
				 ? new CalendarEntity(request.getYear(), request.getMonth(), request.getDay())
				 : calendarEntity;
	}

	private CustomerEntity findCustomerEntity(AppointmentRequest request) {
		return customerRepository.findByJobId(request.getJobId());
	}
}
