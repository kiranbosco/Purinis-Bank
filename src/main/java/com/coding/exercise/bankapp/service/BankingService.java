package com.coding.exercise.bankapp.service;

import com.coding.exercise.bankapp.domain.AccountInformation;
import com.coding.exercise.bankapp.domain.CustomerDetails;
import com.coding.exercise.bankapp.domain.TransactionDetails;
import com.coding.exercise.bankapp.domain.TransferDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BankingService {

    public List<CustomerDetails> findAll() throws Exception;

    public ResponseEntity<Object> addCustomer(CustomerDetails customerDetails) throws Exception;
    
    public CustomerDetails findByCustomerNumber(Long customerNumber);
    
    public ResponseEntity<Object> updateCustomer(CustomerDetails customerDetails, Long customerNumber);
    
    public ResponseEntity<Object> deleteCustomer(Long customerNumber) ;
    
    public ResponseEntity<Object> findByAccountNumber(Long accountNumber);
    
    public ResponseEntity<Object> addNewAccount(AccountInformation accountInformation, Long customerNumber);
    
    public ResponseEntity<Object> transferDetails(TransferDetails transferDetails, Long customerNumber);
    
    public List<TransactionDetails> findTransactionsByAccountNumber(Long accountNumber);
    
}
