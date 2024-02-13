package com.benco.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.benco.portfolio.beans.requests.CustomerRequest;
import com.benco.portfolio.beans.responses.CustomerResponse;
import com.benco.portfolio.services.CustomerService;
@RestController
public class CustomerController {

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	private CustomerService customerService;

	@PostMapping("/addCustomer")
	public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest request) {
		return customerService.createCustomer(request);
	}
}
