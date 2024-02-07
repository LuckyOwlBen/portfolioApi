package com.benco.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.benco.portfolio.entities.CalendarEntity;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

}
