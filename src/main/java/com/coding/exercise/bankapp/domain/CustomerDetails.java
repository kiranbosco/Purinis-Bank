package com.coding.exercise.bankapp.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDetails {

    private String firstName;
    
    private String lastName;
    
    private String middleName;
    
    private Long customerNumber;
    
    private Integer status;
    
    private AddressDetails customerAddress;
    
    private ContactDetails contactDetails;
    
}
