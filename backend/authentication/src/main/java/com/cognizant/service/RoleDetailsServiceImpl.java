package com.cognizant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.Role;
import com.cognizant.repository.RoleRepo;

@Service
public class RoleDetailsServiceImpl {

	@Autowired
	private RoleRepo roleRepo;

	public int getMaxId() {
		int id = roleRepo.getMaxId();
		return id;
	}
	public Role getRoleByName(String name) {
		Optional<Role> op =roleRepo.findByRoleName(name);
		return op.get();
	}
	public Role getRoleById(int id) {
		Optional<Role> op =roleRepo.findById(id);
		return op.get();
	}
}
