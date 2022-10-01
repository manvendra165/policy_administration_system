package com.cognizant.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Model class for storing user data in database
 *
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MyUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int userId;

	@Column(name = "name")
	//@NotBlank(message = "User name cannot be empty")
	private String userName;

	//public String getUserName() {
	//	return userName;
	//}

	//public void setUserName(String userName) {
	//	this.userName = userName;
	//}

	//@NotBlank(message = "Password cannot be blank")
	//@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String password;

	//public String getPassword() {
	//	return password;
	//}

	//public void setPassword(String password) {
	//	this.password = password;
	//}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="role_id",unique=true,nullable=false)
	private Role role;
	

}
