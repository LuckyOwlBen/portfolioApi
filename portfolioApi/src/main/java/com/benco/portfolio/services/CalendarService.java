package com.benco.portfolio.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.benco.portfolio.beans.CalendarEntry;
import com.benco.portfolio.beans.requests.AppointmentRequest;
import com.benco.portfolio.beans.responses.AppointmentResponse;
import com.benco.portfolio.beans.responses.AvailabilityResponse;
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

	public ResponseEntity<AvailabilityResponse> checkAvailabilityForDateRange(String firstDate, String secondDate) {
		LocalDate firstConvertedDate = convertStringToLocalDate(firstDate);
		LocalDate secondConvertedDate = convertStringToLocalDate(secondDate);
		List<CalendarEntity> calendarEntities = calendarRepository.findByDateRange(firstConvertedDate, secondConvertedDate);
		List<CalendarEntry> calendarEntries = convertEntitiesForResponse(calendarEntities);
		return new ResponseEntity<>(new AvailabilityResponse(calendarEntries), HttpStatus.OK);
	}

	private List<CalendarEntry> convertEntitiesForResponse(List<CalendarEntity> calendarEntities) {
		List<CalendarEntry> entries = new ArrayList<>();
		for(CalendarEntity calendarEntity: calendarEntities) {
			entries.add(new CalendarEntry(
				calendarEntity.getDate().toString(),
				calendarEntity.getAvailabilityBitMap()
			));
		}
		return entries;
	}

	private List<Integer> convertStringToIntList(String date) {
		List<String> brokenDate = Arrays.asList(date.split("/"));
		List<Integer> convertedDate = new ArrayList<>();
		for(String dateSegment: brokenDate) {
			convertedDate.add(Integer.decode(dateSegment));
		}
		return convertedDate;
	}

	private LocalDate convertStringToLocalDate(String date) {
		List<Integer> dateList = convertStringToIntList(date);
		return LocalDate.of(dateList.get(0), dateList.get(1), dateList.get(2));		
	}
}
