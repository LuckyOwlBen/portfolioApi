package com.benco.portfolio.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
		Optional<CustomerEntity> optionalCustomerEntity =
				customerRepository.findByEmailId(request.getEmailId());
		boolean isCustomerNew = optionalCustomerEntity.isEmpty();
		CustomerEntity customerEntity = isCustomerNew
				? generateNewCustomer(request)
				: optionalCustomerEntity.get();
		String jwtToken = generateAuthToken(customerEntity);
		HttpStatus status = isCustomerNew
				? HttpStatus.CREATED
				: HttpStatus.CONFLICT;

		 return new ResponseEntity<>(new CustomerResponse(isCustomerNew, customerEntity.getJobId(), jwtToken), status);
	}

	private String generateAuthToken(CustomerEntity customerEntity) {
		return authenticationRepository
				.save(new AuthenticationEntity(customerEntity, jwtUtil
						.generateToken(customerEntity
								.getEmailId())))
				.getJwtKey(); 		
	}

	private CustomerEntity generateNewCustomer(CustomerRequest request) {
		customerRepository.saveAndFlush(new CustomerEntity(request, generateFirstRole()));
		return customerRepository.findByEmailId(request.getEmailId()).orElseThrow();
	}

	private Set<UserRoleEntity> generateFirstRole() {
		Set<UserRoleEntity> roles = new HashSet<>();
		UserRoleEntity userRoleEntity = new UserRoleEntity(Roles.PERSPECTIVE);
		roles.add(userRoleEntity);
		userRoleRepository.save(userRoleEntity);
		return roles;
		
	}
}
