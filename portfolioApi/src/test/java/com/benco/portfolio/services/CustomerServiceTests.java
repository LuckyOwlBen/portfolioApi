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

import com.benco.portfolio.beans.requests.CustomerRequest;
import com.benco.portfolio.beans.responses.CustomerResponse;
import com.benco.portfolio.entities.CustomerEntity;
import com.benco.portfolio.repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTests {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	void existingCustomerInDbTest() {
		CustomerRequest customerRequest = new CustomerRequest();
		CustomerEntity customerEntity = new CustomerEntity();
		when(customerRepository.findByJobId(any())).thenReturn(customerEntity);
		ResponseEntity<CustomerResponse> response = customerService.createCustomer(customerRequest);
		assertFalse(response.getBody().isSuccess());
	}

	@Test
	void nullEntityFromDbTest() {
		CustomerRequest customerRequest = new CustomerRequest();
		CustomerEntity customerEntity = null;
		when(customerRepository.findByJobId(any())).thenReturn(customerEntity);
		ResponseEntity<CustomerResponse> response = customerService.createCustomer(customerRequest);
		assertTrue(response.getBody().isSuccess());
	}

}
