package com.benco.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.benco.portfolio.entities.AuthTableEntity;

public interface AuthTableRepository extends JpaRepository<AuthTableEntity, Long> {
	public AuthTableEntity findByJobId(Long jobId);

}
