package com.coding.exercise.bankapp.service;


import com.coding.exercise.bankapp.domain.AccountInformation;
import com.coding.exercise.bankapp.domain.CustomerDetails;
import com.coding.exercise.bankapp.domain.TransactionDetails;
import com.coding.exercise.bankapp.domain.TransferDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BankingServiceImpl implements BankingService {


	@Override
	public List<CustomerDetails> findAll() {
		return null;
	}

	@Override
	public ResponseEntity<Object> addCustomer(CustomerDetails customerDetails) {
		return null;
	}

	@Override
	public CustomerDetails findByCustomerNumber(Long customerNumber) {
		return null;
	}

	@Override
	public ResponseEntity<Object> updateCustomer(CustomerDetails customerDetails, Long customerNumber) {
		return null;
	}

	@Override
	public ResponseEntity<Object> deleteCustomer(Long customerNumber) {
		return null;
	}

	@Override
	public ResponseEntity<Object> findByAccountNumber(Long accountNumber) {
		return null;
	}

	@Override
	public ResponseEntity<Object> addNewAccount(AccountInformation accountInformation, Long customerNumber) {
		return null;
	}

	@Override
	public ResponseEntity<Object> transferDetails(TransferDetails transferDetails, Long customerNumber) {
		return null;
	}

	@Override
	public List<TransactionDetails> findTransactionsByAccountNumber(Long accountNumber) {
		return null;
	}
}
