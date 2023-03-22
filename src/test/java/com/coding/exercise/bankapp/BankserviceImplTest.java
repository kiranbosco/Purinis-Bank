package com.coding.exercise.bankapp;

import com.coding.exercise.bankapp.model.Customer;
import com.coding.exercise.bankapp.repository.AccountRepository;
import com.coding.exercise.bankapp.repository.CustomerAccountXRefRepository;
import com.coding.exercise.bankapp.repository.CustomerRepository;
import com.coding.exercise.bankapp.repository.TransactionRepository;
import com.coding.exercise.bankapp.service.BankingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BankserviceImplTest {

    private BankingServiceImpl bankingService;

    private Customer customer;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerAccountXRefRepository customerAccountXRefRepository;
    @Mock
    private TransactionRepository transactionRepository;

    @Before
    public void setup() {
        bankingService = new BankingServiceImpl(customerRepository);
        customer = new Customer();
        customer.setFirstName("Arushi");
        customer.setLastName("Purini");
        customer.setStatus(2);
        customer.setCustomerNumber(999l);
        customer.setUpdateDateTime(new Date());

    }

    @Test
    public void bankSuccessData() {

    }
}
