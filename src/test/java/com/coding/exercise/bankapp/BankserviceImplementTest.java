package com.coding.exercise.bankapp;

import com.coding.exercise.bankapp.repository.CustomerRepository;
import com.coding.exercise.bankapp.service.BankingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BankserviceImplementTest {

    private BankingServiceImpl bankingService;

    @Mock
    private CustomerRepository customerRepository;

}
