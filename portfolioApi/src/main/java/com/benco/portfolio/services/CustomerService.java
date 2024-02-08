package com.benco.portfolio.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.benco.portfolio.beans.requests.CustomerRequest;
import com.benco.portfolio.beans.responses.CustomerResponse;
import com.benco.portfolio.entities.CustomerEntity;
import com.benco.portfolio.repositories.CustomerRepository;

@Service
public class CustomerService {

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	private CustomerRepository customerRepository;

	public ResponseEntity<CustomerResponse> addCustomerService(CustomerRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		CustomerEntity customerEntity = customerRepository.findByJobId(request.getJobId());
		 if(customerEntity == null) {
			 customerRepository.save(new CustomerEntity(request));
			 status = HttpStatus.CREATED;
		 }
		 return new ResponseEntity<>(new CustomerResponse(status == HttpStatus.CREATED), status);
	}
}
