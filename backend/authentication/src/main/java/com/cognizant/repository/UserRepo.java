package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.model.MyUser;

/**
 * Repository class for storing, fetching and manipulating user data
 */
@Repository
public interface UserRepo extends JpaRepository<MyUser, Integer> {

	// Method to find a user details with user name
	public MyUser findByUserName(String name);
	public MyUser findByUserNameAndPassword(String userName,String password);
	@Query(value="select max(id) from my_user",nativeQuery=true)
	public int getMaxId();
}
