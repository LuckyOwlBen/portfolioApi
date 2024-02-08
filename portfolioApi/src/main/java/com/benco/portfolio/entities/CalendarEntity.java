package com.benco.portfolio.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="calendar_entity")
public class CalendarEntity {

	public CalendarEntity(int year, int month, int day) {
		setDate(LocalDate.of(year, month, day));
	}

	public CalendarEntity() {}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name="date")
	private LocalDate date;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="morning")
	private CustomerEntity morning;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mid_day")
	private CustomerEntity midDay;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="afternoon")
	private CustomerEntity afternoon;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="evening")
	private CustomerEntity evening;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public CustomerEntity getMorning() {
		return morning;
	}

	public void setMorning(CustomerEntity morning) {
		this.morning = morning;
	}

	public CustomerEntity getMidDay() {
		return midDay;
	}

	public void setMidDay(CustomerEntity midDay) {
		this.midDay = midDay;
	}

	public CustomerEntity getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(CustomerEntity afternoon) {
		this.afternoon = afternoon;
	}

	public CustomerEntity getEvening() {
		return evening;
	}

	public void setEvening(CustomerEntity evening) {
		this.evening = evening;
	}

	public boolean bookAppointment(CustomerEntity customerEntity, int timeSlot) {
			switch(timeSlot) {
				case 1: setMorning(customerEntity);
				return true;
				case 2: setMidDay(customerEntity);
				return true;
				case 3: setAfternoon(customerEntity);
				return true;
				case 4: setEvening(customerEntity);
				return true;
				default: return false;
			}
	}

	public boolean checkAvailability(int timeSlot) {
		switch(timeSlot) {
			case 1: return getMorning() == null;
			case 2: return getMidDay() == null;
			case 3: return getAfternoon() == null;
			case 4: return getEvening() == null;
			default: return false;
		}
	}
}
