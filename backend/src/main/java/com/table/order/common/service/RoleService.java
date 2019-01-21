package com.table.order.common.service;

import org.springframework.stereotype.Service;

import com.table.order.common.repository.RoleRepository;
import com.table.order.common.security.model.Role;
import com.table.order.common.security.model.RoleName;

@Service
public class RoleService {

	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role getClientRole() {
		return roleRepository.findByName(RoleName.ROLE_CLIENT.name());
	}

	public Role getRestaurateurRole() {
		return roleRepository.findByName(RoleName.ROLE_RESTAURATEUR.name());
	}
}
