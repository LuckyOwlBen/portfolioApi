package com.benco.portfolio.beans.requests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerRequestTests {

	@Test
	void coverageTest() {
		CustomerRequest request = new CustomerRequest();
		request.setFirstName("dan");
		request.setLastName("brown");
		assertNotNull(request.getFirstName());
		assertNotNull(request.getLastName());
	}
}
