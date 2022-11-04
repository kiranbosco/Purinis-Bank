package com.coding.exercise.bankapp.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BankInformation {

	private String branchName;
	
	private Integer branchCode;
	
	private AddressDetails branchAddress;
	
	private Integer routingNumber;
}
