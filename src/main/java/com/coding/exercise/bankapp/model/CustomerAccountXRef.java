package com.coding.exercise.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerAccountXRef {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUST_ACC_XREF_ID")
	private UUID id;
	
	private Long accountNumber;
	
	private Long customerNumber;
	
}
