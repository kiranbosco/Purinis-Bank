package com.coding.exercise.bankapp.service;

import com.coding.exercise.bankapp.domain.AccountInformation;
import com.coding.exercise.bankapp.domain.CustomerDetails;
import com.coding.exercise.bankapp.domain.TransactionDetails;
import com.coding.exercise.bankapp.domain.TransferDetails;
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
    private CustomerAccountXRefRepository custAccXRefRepository;
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

    public CustomerDetails findByCustomerNumber(Long customerNumber) throws Exception {
        Optional<Customer> customerEntityOpt = customerRepository.findByCustomerNumber(customerNumber);
        if (!customerEntityOpt.isPresent()) {
            throw new Exception("Data Not found..!");
        }
        return bankingServiceHelper.convertToCustomerDomain(customerEntityOpt.get());
    }


    @Override
    public ResponseEntity<Object> updateCustomer(CustomerDetails customerDetails, Long customerNumber) {

        // get the customerNumber first
        Optional<Customer> manageCustomerEntityOpt = this.customerRepository.findByCustomerNumber(customerNumber);
        // get the bankingServiceHelper data
        Customer unmanageCustomerEntity = bankingServiceHelper.convertoCustomerEntity(customerDetails);
        if (manageCustomerEntityOpt.isPresent()) {
            Customer manageCustomerEntity = manageCustomerEntityOpt.get();
            //Update the conntact details
            if (Optional.ofNullable(unmanageCustomerEntity.getContactDetails()).isPresent()) {
                Contact manageContact = manageCustomerEntity.getContactDetails();
                if (manageContact != null) {
                    manageContact.setEmailId(unmanageCustomerEntity.getContactDetails().getEmailId());
                    manageContact.setHomePhone(unmanageCustomerEntity.getContactDetails().getHomePhone());
                    manageContact.setWorkPhone(unmanageCustomerEntity.getContactDetails().getWorkPhone());
                } else {
                    manageCustomerEntity.setContactDetails(unmanageCustomerEntity.getContactDetails());
                }
                //update address details
                if (Optional.ofNullable(unmanageCustomerEntity.getCustomerAddress()).isPresent()) {
                    Address manageaddress = manageCustomerEntity.getCustomerAddress();
                    if (manageaddress != null) {
                        manageaddress.setCity(unmanageCustomerEntity.getCustomerAddress().getCity());
                        manageaddress.setState(unmanageCustomerEntity.getCustomerAddress().getState());
                        manageaddress.setZip(unmanageCustomerEntity.getCustomerAddress().getZip());
                        manageaddress.setCountry(unmanageCustomerEntity.getCustomerAddress().getCountry());
                        manageaddress.setAddress1(unmanageCustomerEntity.getCustomerAddress().getAddress1());
                        manageaddress.setAddress2(unmanageCustomerEntity.getCustomerAddress().getAddress2());
                    } else {
                        manageCustomerEntity.setCustomerAddress(unmanageCustomerEntity.getCustomerAddress());
                    }
                    manageCustomerEntity.setUpdateDateTime(new Date());
                    manageCustomerEntity.setStatus(unmanageCustomerEntity.getStatus());
                    manageCustomerEntity.setFirstName(unmanageCustomerEntity.getFirstName());
                    manageCustomerEntity.setMiddleName(unmanageCustomerEntity.getMiddleName());
                    manageCustomerEntity.setLastName(unmanageCustomerEntity.getLastName());
                    manageCustomerEntity.setUpdateDateTime(new Date());
                    //save customer update details here
                    customerRepository.save(manageCustomerEntity);
                    return ResponseEntity.status(HttpStatus.OK).body("Customer details are updated..!");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Number " + customerNumber + " not found.");
    }

    @Override
    public ResponseEntity<Object> deleteCustomer(Long customerNumber) {
        Optional<Customer> deleteCustomerId = this.customerRepository.findByCustomerNumber(customerNumber);
        if (!deleteCustomerId.isPresent()) {
            throw new RuntimeException("Data not found..!");
        } else {
            Customer deleteCustInfo = deleteCustomerId.get();
            customerRepository.delete(deleteCustInfo);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted customer Id");
    }

    @Override
    public ResponseEntity<Object> softDeleteCustomerId(Long customerNumber) throws Exception {
        //get customer Id is available o not
        Optional<Customer> softdelete = this.customerRepository.findByCustomerNumber(customerNumber);
        if (!softdelete.isPresent()) {
            throw new Exception("Data not found given customer Id..!");
        }
        // if customer status already 0 we have to check this condition
        if (softdelete.get().getStatus() == 0) {
            throw new Exception("Given Customer id already in-active status");
        }
        //if custoemr data not 0 we have to set zero value here
        softdelete.get().setStatus(0);
        this.customerRepository.save(softdelete.get());
        return ResponseEntity.status(HttpStatus.OK).body("Customer successfully deleted..!");
    }


    @Override
    public AccountInformation findByAccountNumber(Long accountNumber) throws Exception {
        Optional<Account> findcustAccnum = this.accountRepository.findByAccountNumber(accountNumber);
        if (!findcustAccnum.isPresent()) {
            throw new Exception("Customer account not found..!");
        }
        return bankingServiceHelper.convertToAccountDomain(findcustAccnum.get());
    }

    public ResponseEntity<Object> addNewAccount(AccountInformation accountInformation, Long customerNumber) {

        Optional<Customer> customerEntityOpt = customerRepository.findByCustomerNumber(customerNumber);

        if (customerEntityOpt.isPresent()) {
            accountRepository.save(bankingServiceHelper.convertToAccountEntity(accountInformation));

            // Add an entry to the CustomerAccountXRef
            custAccXRefRepository.save(CustomerAccountXRef.builder()
                    .accountNumber(accountInformation.getAccountNumber())
                    .customerNumber(customerNumber)
                    .build());

        }

        return ResponseEntity.status(HttpStatus.CREATED).body("New Account created successfully.");
    }

    public ResponseEntity<Object> transferDetails(TransferDetails transferDetails, Long customerNumber) {

        List<Account> accountEntities = new ArrayList<>();
        Account fromAccountEntity = null;
        Account toAccountEntity = null;

        Optional<Customer> customerEntityOpt = customerRepository.findByCustomerNumber(customerNumber);

        // If customer is present
        if(customerEntityOpt.isPresent()) {

            // get FROM ACCOUNT info
            Optional<Account> fromAccountEntityOpt = accountRepository.findByAccountNumber(transferDetails.getFromAccountNumber());
            if(fromAccountEntityOpt.isPresent()) {
                fromAccountEntity = fromAccountEntityOpt.get();
            }
            else {
                // if from request does not exist, 404 Bad Request
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("From Account Number " + transferDetails.getFromAccountNumber() + " not found.");
            }


            // get TO ACCOUNT info
            Optional<Account> toAccountEntityOpt = accountRepository.findByAccountNumber(transferDetails.getToAccountNumber());
            if(toAccountEntityOpt.isPresent()) {
                toAccountEntity = toAccountEntityOpt.get();
            }
            else {
                // if from request does not exist, 404 Bad Request
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("To Account Number " + transferDetails.getToAccountNumber() + " not found.");
            }


            // if not sufficient funds, return 400 Bad Request
            if(fromAccountEntity.getAccountBalance() < transferDetails.getTransferAmount()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Funds.");
            }
            else {
                synchronized (this) {
                    // update FROM ACCOUNT
                    fromAccountEntity.setAccountBalance(fromAccountEntity.getAccountBalance() - transferDetails.getTransferAmount());
                    fromAccountEntity.setUpdateDateTime(new Date());
                    accountEntities.add(fromAccountEntity);

                    // update TO ACCOUNT
                    toAccountEntity.setAccountBalance(toAccountEntity.getAccountBalance() + transferDetails.getTransferAmount());
                    toAccountEntity.setUpdateDateTime(new Date());
                    accountEntities.add(toAccountEntity);

                    accountRepository.saveAll(accountEntities);

                    // Create transaction for FROM Account
                    Transaction fromTransaction = bankingServiceHelper.createTransaction(transferDetails, fromAccountEntity.getAccountNumber(), "DEBIT");
                    transactionRepository.save(fromTransaction);

                    // Create transaction for TO Account
                    Transaction toTransaction = bankingServiceHelper.createTransaction(transferDetails, toAccountEntity.getAccountNumber(), "CREDIT");
                    transactionRepository.save(toTransaction);
                }

                return ResponseEntity.status(HttpStatus.OK).body("Success: Amount transferred for Customer Number " + customerNumber);
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Number " + customerNumber + " not found.");
        }

    }

    /**
     * Get all transactions for a specific account
     *
     * @param accountNumber
     * @return
     */
    public List<TransactionDetails> findTransactionsByAccountNumber(Long accountNumber) {
        List<TransactionDetails> transactionDetails = new ArrayList<>();
        Optional<Account> accountEntityOpt = accountRepository.findByAccountNumber(accountNumber);
        if(accountEntityOpt.isPresent()) {
            Optional<List<Transaction>> transactionEntitiesOpt = transactionRepository.findByAccountNumber(accountNumber);
            if(transactionEntitiesOpt.isPresent()) {
                transactionEntitiesOpt.get().forEach(transaction -> {
                    transactionDetails.add(bankingServiceHelper.convertToTransactionDomain(transaction));
                });
            }
        }

        return transactionDetails;
    }


}
