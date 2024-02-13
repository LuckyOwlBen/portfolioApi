package com.benco.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.benco.portfolio.entities.AuthenticationEntity;

public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, Long> {

}
