package com.coding.exercise.bankapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.coding.exercise.bankapp.model.*;
import com.coding.exercise.bankapp.repository.AccountRepository;
import com.coding.exercise.bankapp.repository.CustomerAccountXRefRepository;
import com.coding.exercise.bankapp.repository.CustomerRepository;
import com.coding.exercise.bankapp.repository.TransactionRepository;
import com.coding.exercise.bankapp.service.helper.BankingServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.coding.exercise.bankapp.domain.AccountInformation;
import com.coding.exercise.bankapp.domain.CustomerDetails;
import com.coding.exercise.bankapp.domain.TransactionDetails;
import com.coding.exercise.bankapp.domain.TransferDetails;


@Service
@Transactional
public class BankingServiceImpl implements BankingService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerAccountXRefRepository custAccXRefRepository;
    @Autowired
    private BankingServiceHelper bankingServiceHelper;

    public BankingServiceImpl(CustomerRepository repository) {
        this.customerRepository = repository;
    }


    @Override
    public List<CustomerDetails> findAll() {
        List<CustomerDetails> allCustDetails = new ArrayList<>();
        Iterable<Customer> listdetails = customerRepository.findAll();
        listdetails.forEach(customer -> {
            allCustDetails.add(bankingServiceHelper.convertCustomerDetails(customer));
        });
        return allCustDetails;
    }

    @Override
    public ResponseEntity<Object> addCustomer(CustomerDetails customerDetails) {

        Customer customer = bankingServiceHelper.covertCustomerdetails(customerDetails);
        customer.setCreateDateTime(new Date());
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("New account has created ..!");
    }


    @Override
    public ResponseEntity<String> createCustomerData(Customer customer) {

        Customer customer1 = new Customer();
        customer1.setCreateDateTime(new Date());
        customer1 = saveCustomerDetails(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("New account has created ");
    }

    public Customer saveCustomerDetails(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerDetails findByCustomerNumber(Long customerNumber) throws Exception {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerNumber(customerNumber);
        if (!optionalCustomer.isPresent()) {
            throw new RuntimeException("Customer not availabel please contact admi..!");
        }
        return bankingServiceHelper.convertCustomerDetails(optionalCustomer.get());
    }

    /**
     * UPDATE Customer
     *
     * @param customerDetails
     * @param customerNumber
     * @return
     */
    @Override
    public ResponseEntity<Object> updateCustomer(CustomerDetails customerDetails, Long customerNumber) {
        Optional<Customer> manageCustomerDetails = this.customerRepository.findByCustomerNumber(customerNumber);
        Customer unmanagedCustomerDetails = bankingServiceHelper.covertCustomerdetails(customerDetails);
        // check customers ate available or not

        if (manageCustomerDetails.isPresent()) {
            Customer manageEntityDetails = manageCustomerDetails.get();
            // write the if condition , if contact details are not empty you can update the contact details

            if (Optional.ofNullable(unmanagedCustomerDetails.getContactDetails()).isPresent()) {
                Contact manageContactDetails = manageEntityDetails.getContactDetails();
                if (manageCustomerDetails != null) {
                    manageContactDetails.setWorkPhone(unmanagedCustomerDetails.getContactDetails().getHomePhone());
                    manageContactDetails.setHomePhone(unmanagedCustomerDetails.getContactDetails().getHomePhone());
                    manageContactDetails.setEmailId(unmanagedCustomerDetails.getContactDetails().getEmailId());
                } else {
                    manageEntityDetails.setContactDetails(unmanagedCustomerDetails.getContactDetails());
                }
            }
        }

        return null;
    }

    @Override
    public ResponseEntity<Object> deleteCustomer(Long customerNumber) {

        Optional<Customer> manageCustomerEntityOpt = this.customerRepository.findByCustomerNumber(customerNumber);
        if (manageCustomerEntityOpt.isPresent()) {
            Customer manageCustomerEntity = manageCustomerEntityOpt.get();
            customerRepository.delete(manageCustomerEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted by customer Id " + customerNumber);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find customer Number " + customerNumber);

        }
    }

    @Override
    public ResponseEntity<Object> findByAccountNumber(AccountInformation accountInformation, Long accountNumber) {
        return null;
    }


    public ResponseEntity<Object> findByAccountNumber(Long accountNumber) {

        Optional<Account> accountEntityOpt = accountRepository.findByAccountNumber(accountNumber);

        if(accountEntityOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(bankingServiceHelper.convertToAccountDomain(accountEntityOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Number " + accountNumber + " not found.");
        }

    }

    @Override
    public ResponseEntity<Object> addNewAccount(AccountInformation accountInformation, Long customerNumber) {
     //find the account number is available or not
        Optional<Account> accountNumberEntityOpt = this.accountRepository.findByAccountNumber(customerNumber);
        if (accountNumberEntityOpt.isPresent()) {
            this.accountRepository.save(bankingServiceHelper.convertToAccountEntity(accountInformation));
        }
        // Add an entry to the CustomerAccountXRef
        custAccXRefRepository.save(CustomerAccountXRef.builder()
                .accountNumber(accountInformation.getAccountNumber())
                .customerNumber(customerNumber)
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).body("New Account created successfully.");
    }

    @Override
    public ResponseEntity<Object> transferDetails(TransferDetails transferDetails, Long customerNumber) {

        return null;
    }

    @Override
    public List<TransactionDetails> findTransactionsByAccountNumber(Long accountNumber) {
        return null;
    }

    @Override
    public void softDeteletCustomerId(Long accountNumber) {

    }

}