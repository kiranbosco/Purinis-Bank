package com.coding.exercise.bankapp.domain;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountInformation {

	private Long accountNumber;
	
	private BankInformation bankInformation;
	
	private String accountStatus;
	
	private String accountType;
	
	private Double accountBalance;
	
	private Date accountCreated;
}
