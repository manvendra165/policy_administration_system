package com.cognizant.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer roleId;
	private String roleName;
	@OneToMany(mappedBy="role")
	private List<MyUser> users;
}
