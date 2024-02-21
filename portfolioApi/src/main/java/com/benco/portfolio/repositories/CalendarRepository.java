package com.benco.portfolio.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.benco.portfolio.beans.constants.QueryConstants;
import com.benco.portfolio.entities.CalendarEntity;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

	public CalendarEntity findByDate(LocalDate date);

	@Query(QueryConstants.FIND_CALENDAR_ENTITIES_BY_DATE_RANGE)
	public List<CalendarEntity> findByDateRange(LocalDate startDate, LocalDate endDate);

}
