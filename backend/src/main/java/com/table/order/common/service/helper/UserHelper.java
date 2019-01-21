package com.table.order.common.service.helper;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.table.order.common.repository.UserRepository;
import com.table.order.common.security.model.RoleName;
import com.table.order.common.security.model.User;

@Component
public class UserHelper {

	private UserRepository userRepository;
	
	@Autowired
	public UserHelper(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean isLoggedRestaurateur() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		return isRestaurateur(authorities);
	}
	
	private boolean isRestaurateur(Collection<? extends GrantedAuthority> authorities) {
		return !authorities.isEmpty() && 
				authorities.iterator().next().getAuthority().equals(RoleName.ROLE_RESTAURATEUR.name());
	}
	
	public User getLoggedUser() {
		return userRepository.findByUsername(getLoggedUserUsername());
	}

	public String getLoggedUserUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null) 
			return null;
		
		Object principal = authentication.getPrincipal();
		
		if (principal.getClass() == UserDetails.class) 
			return ((UserDetails) principal).getUsername();
		
		if (principal.getClass() == String.class)
			return principal.toString();
		
		return null;
	}

}
