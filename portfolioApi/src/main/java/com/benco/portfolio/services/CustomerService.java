package com.benco.portfolio.services;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.benco.portfolio.beans.requests.CustomerRequest;
import com.benco.portfolio.beans.responses.CustomerResponse;
import com.benco.portfolio.entities.AuthenticationEntity;
import com.benco.portfolio.entities.CustomerEntity;
import com.benco.portfolio.entities.UserRoleEntity;
import com.benco.portfolio.enums.Roles;
import com.benco.portfolio.repositories.AuthenticationRepository;
import com.benco.portfolio.repositories.CustomerRepository;
import com.benco.portfolio.repositories.UserRoleRepository;
import com.benco.portfolio.util.JwtUtil;

import jakarta.persistence.NonUniqueResultException;

@Service
public class CustomerService {

	public CustomerService(
			CustomerRepository customerRepository,
			UserRoleRepository userRoleRepository,
			JwtUtil jwtUtil,
			AuthenticationRepository authenticationRepository
		) {
		this.customerRepository = customerRepository;
		this.userRoleRepository = userRoleRepository;
		this.jwtUtil = jwtUtil;
		this.authenticationRepository = authenticationRepository;
	}

	private CustomerRepository customerRepository;

	private UserRoleRepository userRoleRepository;

	private AuthenticationRepository authenticationRepository;

	private JwtUtil jwtUtil;

	public ResponseEntity<CustomerResponse> createCustomer(CustomerRequest request) {
		customerRepository
			.findByEmailId(request.getEmailId())
			.stream()
			.findFirst()
			.map(existingCustomer -> {
				throw new ResponseStatusException(
					HttpStatus.CONFLICT,
					"Customer already exists"
				);
			});
		CustomerEntity customerEntity = generateNewCustomer(request);
		return new ResponseEntity<CustomerResponse>(new CustomerResponse(customerEntity.getJobId(), generateAuthToken(customerEntity)), HttpStatus.CREATED);
	}

	private String generateAuthToken(CustomerEntity customerEntity) {
		AuthenticationEntity authenticationEntity = authenticationRepository.findByCustomerReference(customerEntity);
		if (authenticationEntity == null) {
			return authenticationRepository
					.save(new AuthenticationEntity(customerEntity, jwtUtil.generateToken(customerEntity.getEmailId())))
					.getJwtKey();
		}
		return authenticationEntity.getJwtKey();
	}

	private CustomerEntity generateNewCustomer(CustomerRequest request) throws NonUniqueResultException, NoSuchElementException {
		customerRepository.saveAndFlush(new CustomerEntity(request, generateFirstRole()));
		return customerRepository.findByEmailId(request.getEmailId())
			.stream()
			.findFirst()
			.map(customer -> customer)
			.orElseThrow()
			.orElseThrow();
	}

	private Set<UserRoleEntity> generateFirstRole() {
		Set<UserRoleEntity> roles = new HashSet<>();
		UserRoleEntity userRoleEntity = new UserRoleEntity(Roles.PERSPECTIVE);
		roles.add(userRoleEntity);
		userRoleRepository.save(userRoleEntity);
		return roles;
		
	}
}
