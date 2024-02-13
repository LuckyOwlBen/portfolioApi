package com.benco.portfolio.beans;

import org.springframework.security.core.GrantedAuthority;

import com.benco.portfolio.entities.UserRoleEntity;

public class CustomGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -6731572255637273670L;

	private String authority;

	public CustomGrantedAuthority(UserRoleEntity userRoleEntity) {
		this.authority = userRoleEntity.getName();
	}

	@Override
	public String getAuthority() {
		return authority;
	}
}
