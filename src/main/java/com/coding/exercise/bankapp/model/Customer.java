package com.coding.exercise.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    @Column(name="CUST_ID")
    private UUID id;
    
    private String firstName;
    
    private String lastName;
    
    private String middleName;
    
    private Long customerNumber;
    
    private String status;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Address customerAddress;
    
    @OneToOne(cascade=CascadeType.ALL)
    private Contact contactDetails;
    
    @Temporal(TemporalType.TIME)
	private Date createDateTime;
	
    @Temporal(TemporalType.TIME)
	private Date updateDateTime;
	
}
