package com.benco.portfolio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.benco.portfolio.beans.CustomGrantedAuthority;
import com.benco.portfolio.beans.CustomUserDetails;
import com.benco.portfolio.entities.CustomerEntity;
import com.benco.portfolio.entities.UserRoleEntity;
import com.benco.portfolio.repositories.CustomerRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private CustomerRepository customerRepository;

	public UserDetailsServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<CustomerEntity> customerEntity = customerRepository.findByEmailId(username)
		.stream()
		.findFirst()
		.orElseThrow();
		if (customerEntity.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (UserRoleEntity userRoleEntity : customerEntity.get().getRoles()) {
			grantedAuthorities.add(new CustomGrantedAuthority(userRoleEntity));
		}
		return new CustomUserDetails(customerEntity.get().getEmailId(), customerEntity.get().getJobId(),
				grantedAuthorities);
	}

}
