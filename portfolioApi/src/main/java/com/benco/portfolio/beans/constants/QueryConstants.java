package com.benco.portfolio.beans.constants;

public class QueryConstants {

	public static final String FIND_CALENDAR_ENTITIES_BY_DATE_RANGE = "SELECT e FROM CalendarEntity e WHERE e.date BETWEEN ?1 AND ?2";

	private QueryConstants() {}
}
