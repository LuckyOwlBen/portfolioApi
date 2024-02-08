package com.benco.portfolio.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CalendarEntityTests {

	@Test
	void afternoonBookingTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		CalendarEntity calendarEntity = new CalendarEntity();
		for(int i = 1; i < 5; i++) {
			assertTrue(calendarEntity.checkAvailability(i));
			assertTrue(calendarEntity.bookAppointment(customerEntity, i));
			assertFalse(calendarEntity.checkAvailability(i));
		}
		
	}

	@Test
	void unknownBookingTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		CalendarEntity calendarEntity = new CalendarEntity();
		assertFalse(calendarEntity.checkAvailability(0));
		assertFalse(calendarEntity.bookAppointment(customerEntity, 314342342));
		assertFalse(calendarEntity.checkAvailability(-999));
	}

	@Test
	void coverageTest() {
		CalendarEntity calendarEntity = new CalendarEntity();
		calendarEntity.setDate(LocalDate.of(2024, 11, 11));
		calendarEntity.setId(1L);
		assertNotNull(calendarEntity.getDate());
		assertNotNull(calendarEntity.getId());
	}

}
