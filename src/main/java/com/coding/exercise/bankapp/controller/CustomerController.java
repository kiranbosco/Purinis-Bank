package com.coding.exercise.bankapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coding.exercise.bankapp.domain.CustomerDetails;
import com.coding.exercise.bankapp.service.BankingServiceImpl;


@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private BankingServiceImpl bankingService;

    @GetMapping(path = "/all")
    public List<CustomerDetails> getAllCustomers() throws Exception {
        return bankingService.findAll();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerDetails customer) throws Exception {
        return bankingService.addCustomer(customer);
    }

    @GetMapping(path = "/{customerNumber}")
    public CustomerDetails getCustomer(@PathVariable Long customerNumber) throws Exception {

        return bankingService.findByCustomerNumber(customerNumber);
    }

    @PutMapping(path = "/{customerNumber}")
    public ResponseEntity<Object> updatecustomer(@RequestBody CustomerDetails customerDetails, @PathVariable Long customerNumber) {
        return bankingService.updateCustomer(customerDetails, customerNumber);

    }
/*    @DeleteMapping("/{customerNumber}")
    public ResponseEntity<Object> deleteCustomerdeta(@PathVariable Long customerNumber){
        return bankingService.deleteCustomer(customerNumber);
    }*/
    @DeleteMapping("{/customerNumber}")
    public ResponseEntity<Object> custSoftdelete(@PathVariable Long customerNumber) throws Exception {
         bankingService.softDeleteCustomerId(customerNumber);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted..!");
    }
}
