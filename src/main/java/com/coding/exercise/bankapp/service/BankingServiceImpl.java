package com.coding.exercise.bankapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.coding.exercise.bankapp.model.Address;
import com.coding.exercise.bankapp.model.Contact;
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

    @Override
    public void softDeteletCustomerId(Long accountNumber) {

    }

}