package com.benco.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.benco.portfolio.entities.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

}
