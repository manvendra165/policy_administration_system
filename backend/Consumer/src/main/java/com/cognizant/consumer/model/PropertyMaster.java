package com.cognizant.consumer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class PropertyMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long id;

	@NotBlank
	@Size(max = 30)
	@Column(name = "Insurance_Type")
	private String insurancetype;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Property_Type")
	private String propertytype;

	@NotBlank
	@Size(max = 7)
	@Column(name = "Building_Type")
	private String buildingtype;

	@NotNull
	@Column(name = "Building_Age")
	private Long buildingage;
}
