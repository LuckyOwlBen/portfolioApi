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
@Table(name="authentication")
public class AuthenticationEntity {

	public AuthenticationEntity(CustomerEntity customerEntity, String jwt) {
		this.customerReference = customerEntity;
		this.jwtKey = jwt;
		this.expired = false;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_reference")
	private CustomerEntity customerReference;

	@Column(name="expired")
	private boolean expired;

	@Column(name="jwt_key")
	private String jwtKey;

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

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean isExpired) {
		this.expired = isExpired;
	}

	public String getJwtKey() {
		return jwtKey;
	}

	public void setJwtKey(String jwtKey) {
		this.jwtKey = jwtKey;
	}
}
