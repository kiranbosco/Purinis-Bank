package com.coding.exercise.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADDR_ID")
	private UUID id;
	
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;

}
