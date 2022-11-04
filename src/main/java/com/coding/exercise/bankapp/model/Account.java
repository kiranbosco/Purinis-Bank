package com.coding.exercise.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACCT_ID")
	private UUID id;
	
	private Long accountNumber;
	
	@OneToOne(cascade=CascadeType.ALL)
	private BankInfo bankInformation;
	
	private String accountStatus;
	
	private String accountType;
	
	private Double accountBalance;
    
    @Temporal(TemporalType.TIME)
	private Date createDateTime;
	
    @Temporal(TemporalType.TIME)
	private Date updateDateTime;
}
