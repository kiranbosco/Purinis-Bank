package com.coding.exercise.bankapp.service;

import com.coding.exercise.bankapp.domain.AccountInformation;
import com.coding.exercise.bankapp.domain.CustomerDetails;
import com.coding.exercise.bankapp.domain.TransactionDetails;
import com.coding.exercise.bankapp.domain.TransferDetails;
import com.coding.exercise.bankapp.model.Customer;
import com.coding.exercise.bankapp.repository.AccountRepository;
import com.coding.exercise.bankapp.repository.CustomerAccountXRefRepository;
import com.coding.exercise.bankapp.repository.CustomerRepository;
import com.coding.exercise.bankapp.repository.TransactionRepository;
import com.coding.exercise.bankapp.service.helper.BankingServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BankingServiceImpl implements BankingService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerAccountXRefRepository customerAccountXRefRepository;

    @Autowired
    private BankingServiceHelper bankingServiceHelper;


    public BankingServiceImpl(CustomerRepository repository) {
        this.customerRepository = repository;
    }


    @Override
    public List<CustomerDetails> findAll() throws Exception {
        List<CustomerDetails> allCustomerDetails = new ArrayList<>();
        Iterable<Customer> customerDetails = this.customerRepository.findAll();
        if (customerDetails != null) {
            customerDetails.forEach(customer -> {
                allCustomerDetails.add(bankingServiceHelper.convertToCustomerDomain(customer));
            });
        } else {
            throw new Exception("Data not found exception..!");
        }
        return allCustomerDetails;
    }

    @Override
    public ResponseEntity<Object> addCustomer(CustomerDetails customerDetails) throws Exception {
        Customer customer = bankingServiceHelper.convertoCustomerEntity(customerDetails);
        customer.setCreateDateTime(new Date());
        Optional<Customer> findCustomerId = this.customerRepository.findByCustomerNumber(customer.getCustomerNumber());
        if (findCustomerId.isPresent()) {
            throw new Exception("Sorry customer already existed please try to create new customer id..!");
        }
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data successfully created..!");
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
