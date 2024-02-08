package com.benco.portfolio.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.benco.portfolio.beans.requests.AppointmentRequest;
import com.benco.portfolio.beans.responses.AppointmentResponse;
import com.benco.portfolio.entities.CalendarEntity;
import com.benco.portfolio.entities.CustomerEntity;
import com.benco.portfolio.repositories.CalendarRepository;
import com.benco.portfolio.repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CalendarServiceTest {

	@InjectMocks
	private CalendarService calendarService;

	@Mock
	private CalendarRepository calendarRepository;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	void bookAppointmentTest() {
		AppointmentRequest appointmentRequest = new AppointmentRequest();
		appointmentRequest.setDay(21);
		appointmentRequest.setMonth(3);
		appointmentRequest.setYear(2024);
		appointmentRequest.setTimeslot(1);
		appointmentRequest.setJobId("1234");
		CustomerEntity customerEntity = new CustomerEntity();
		CalendarEntity calendarEntity = new CalendarEntity();
		when(calendarRepository.findByDate(any())).thenReturn(calendarEntity);
		when(customerRepository.findByJobId(any())).thenReturn(customerEntity);
		ResponseEntity<AppointmentResponse> response =
				calendarService.scheduleAppointment(appointmentRequest);
		assertTrue(response.getBody().isSuccess());
	}

	@Test
	void failBookAppointmentTest() {
		AppointmentRequest appointmentRequest = new AppointmentRequest();
		appointmentRequest.setDay(21);
		appointmentRequest.setMonth(3);
		appointmentRequest.setYear(2024);
		appointmentRequest.setTimeslot(1);
		appointmentRequest.setJobId("1234");
		CustomerEntity customerEntity = new CustomerEntity();
		CalendarEntity calendarEntity = new CalendarEntity();
		calendarEntity.setMorning(customerEntity);
		when(calendarRepository.findByDate(any())).thenReturn(calendarEntity);
		when(customerRepository.findByJobId(any())).thenReturn(customerEntity);
		ResponseEntity<AppointmentResponse> response =
				calendarService.scheduleAppointment(appointmentRequest);
		assertFalse(response.getBody().isSuccess());
	}

	@Test
	void fistDayBookAppointmentTest() {
		AppointmentRequest appointmentRequest = new AppointmentRequest();
		appointmentRequest.setDay(21);
		appointmentRequest.setMonth(3);
		appointmentRequest.setYear(2024);
		appointmentRequest.setTimeslot(1);
		appointmentRequest.setJobId("1234");
		CustomerEntity customerEntity = new CustomerEntity();
		CalendarEntity calendarEntity = new CalendarEntity();
		calendarEntity.setMorning(customerEntity);
		when(calendarRepository.findByDate(any())).thenReturn(null);
		when(customerRepository.findByJobId(any())).thenReturn(customerEntity);
		ResponseEntity<AppointmentResponse> response =
				calendarService.scheduleAppointment(appointmentRequest);
		assertTrue(response.getBody().isSuccess());
	}
}
