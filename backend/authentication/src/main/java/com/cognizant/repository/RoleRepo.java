package com.cognizant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

	@Query(value="select max(role_id) from role",nativeQuery=true)
	public int getMaxId();
	public Optional<Role> findByRoleName(String name);
}
