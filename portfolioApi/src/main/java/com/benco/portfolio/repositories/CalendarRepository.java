package com.benco.portfolio.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.benco.portfolio.entities.CalendarEntity;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

	public CalendarEntity findByDate(LocalDate date);

}
