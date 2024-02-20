package com.benco.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.benco.portfolio.entities.AuthenticationEntity;
import com.benco.portfolio.entities.CustomerEntity;

public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, Long> {

	public AuthenticationEntity findByCustomerReference(CustomerEntity entity);
}
