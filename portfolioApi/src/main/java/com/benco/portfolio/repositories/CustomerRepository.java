package com.benco.portfolio.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.benco.portfolio.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	public CustomerEntity findByJobId(String jobId);
	public List<Optional<CustomerEntity>> findByEmailId(String username);
}
