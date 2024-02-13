package com.benco.portfolio.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class AuthenticationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_reference")
	private CustomerEntity customerReference;

	@Column(name="jwt_key")
	private String jwtKey;

	@Column(name="is_expired")
	private boolean expired;

	public AuthenticationEntity(CustomerEntity customerEntity, String jwtKey, boolean expired) {
		this.customerReference = customerEntity;
		this.jwtKey = jwtKey;
		this.expired = expired;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerEntity getCustomerReference() {
		return customerReference;
	}

	public void setCustomerReference(CustomerEntity customerReference) {
		this.customerReference = customerReference;
	}

	public String getJwtKey() {
		return jwtKey;
	}

	public void setJwtKey(String jwtKey) {
		this.jwtKey = jwtKey;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean isExpired) {
		this.expired = isExpired;
	}
}
