package com.benco.portfolio.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.benco.portfolio.beans.requests.CustomerRequest;
import com.benco.portfolio.beans.responses.CustomerResponse;
import com.benco.portfolio.entities.CustomerEntity;
import com.benco.portfolio.enums.Roles;
import com.benco.portfolio.repositories.CustomerRepository;

@Service
public class CustomerService {

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	private CustomerRepository customerRepository;

	public ResponseEntity<CustomerResponse> createCustomer(CustomerRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findByEmailId(request.getEmailId());
		 if(optionalCustomerEntity.isEmpty()) {
			 optionalCustomerEntity = Optional.of(customerRepository.save(new CustomerEntity(request, Roles.PERSPECTIVE)));
			 status = HttpStatus.CREATED;
		 }
		 return new ResponseEntity<>(new CustomerResponse(status == HttpStatus.CREATED, optionalCustomerEntity.get()), status);
	}
}
