package com.benco.portfolio.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.benco.portfolio.entities.CustomerEntity;

@ExtendWith(MockitoExtension.class)
class CustomerEntityTests {

	@Test
	void coverageTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setFirstName("Dan");
		customerEntity.setLastName("Brown");
		customerEntity.setJobId("1234");
		assertNotNull(customerEntity.getFirstName());
		assertNotNull(customerEntity.getLastName());
		assertNotNull(customerEntity.getJobId());
	}
}
