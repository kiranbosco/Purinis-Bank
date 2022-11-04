package com.coding.exercise.bankapp.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressDetails {

	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;
	
}
