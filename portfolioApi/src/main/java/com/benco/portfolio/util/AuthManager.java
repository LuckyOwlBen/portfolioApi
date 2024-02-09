package com.benco.portfolio.util;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AuthManager implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		authentication.setAuthenticated(true);
		return authentication;
	}

}
