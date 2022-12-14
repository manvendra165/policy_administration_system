package com.cognizant.Quotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Table(name="Quotes_Master")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuotesMaster  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long id;
	
	@NotNull
	@Column(name = "Business_Value")
	private Long businessValue;

	@NotNull
	@Column(name = "Property_Value")
	private Long propertyValue;

	@NotBlank
	@Size(max = 50)
	@Column(name = "Property_Type")
	private String propertyType;
	
	@NotBlank
	@Size(max = 50)
	@Column(name = "Quotes")
	private String quotes;
	
	

}
