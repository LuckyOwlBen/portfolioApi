package com.benco.portfolio.entities;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import com.benco.portfolio.beans.requests.CustomerRequest;
import com.benco.portfolio.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_info")
public class CustomerEntity {

	public CustomerEntity(CustomerRequest request, Set<UserRoleEntity> roles) {
		this.firstName = request.getFirstName();
		this.lastName = request.getLastName();
		this.emailId = request.getEmailId();
		this.roles = roles;
	}

	public CustomerEntity() {}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="job_id")
	@UuidGenerator
	private String jobId;

	@Column(name="email_id")
	private String emailId;

	@ManyToMany(fetch = FetchType.EAGER)
	@Column(name="roles")
	private Set<UserRoleEntity> roles = new HashSet<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Set<UserRoleEntity> getRoles() {
		return roles;
	}

	public void addRole(Roles role) {
		this.roles.add(new UserRoleEntity(role));
	}

	public void setRoles(Set<UserRoleEntity> role) {
		this.roles = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
