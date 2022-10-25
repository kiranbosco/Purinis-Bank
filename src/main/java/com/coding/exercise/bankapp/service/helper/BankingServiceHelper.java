package com.coding.exercise.bankapp.service.helper;

import org.springframework.stereotype.Component;

import com.coding.exercise.bankapp.domain.AddressDetails;
import com.coding.exercise.bankapp.domain.BankInformation;
import com.coding.exercise.bankapp.domain.ContactDetails;
import com.coding.exercise.bankapp.domain.CustomerDetails;
import com.coding.exercise.bankapp.domain.TransactionDetails;
import com.coding.exercise.bankapp.model.Address;
import com.coding.exercise.bankapp.model.BankInfo;
import com.coding.exercise.bankapp.model.Contact;
import com.coding.exercise.bankapp.model.Customer;
import com.coding.exercise.bankapp.model.Transaction;

@Component
public class BankingServiceHelper {

    public CustomerDetails convertCustomerDetails(Customer customer) {
        return CustomerDetails.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .middleName(customer.getMiddleName())
                .customerNumber(customer.getCustomerNumber())
                .status(customer.getStatus())
                .contactDetails(convertContactDetails(customer.getContactDetails()))
                .customerAddress(convertAddressDetails(customer.getCustomerAddress())).build();
    }

    public Customer covertCustomerdetails(CustomerDetails customerDetails) {
        return Customer.builder()
                .firstName(customerDetails.getFirstName())
                .lastName(customerDetails.getLastName())
                .middleName(customerDetails.getMiddleName())
                .status(customerDetails.getStatus())
                .contactDetails(convertContacts(customerDetails.getContactDetails()))
                .customerAddress(convertAddress(customerDetails.getCustomerAddress())).build();
    }

    public AddressDetails convertAddressDetails(Address address) {

        return AddressDetails.builder()
                .state(address.getState())
                .address2(address.getAddress2())
                .address1(address.getAddress1())
                .city(address.getCity())
                .zip(address.getZip())
                .country(address.getCountry()).build();
    }
    public  Address convertAddress(AddressDetails addressDetails){
        return Address.builder()
                .address1(addressDetails.getAddress1())
                .address2(addressDetails.getAddress2())
                .state(addressDetails.getState())
                .city(addressDetails.getCity())
                .zip(addressDetails.getZip())
                .country(addressDetails.getCountry())
                .build();
    }
    public  BankInformation convertBankInformation(BankInfo bankInfo){
        return BankInformation.builder()
                .branchCode(bankInfo.getBranchCode())
                .branchName(bankInfo.getBranchName())
                .routingNumber(bankInfo.getRoutingNumber())
                .branchAddress(convertAddressDetails(bankInfo.getBranchAddress()))
                .build();
    }
    public BankInfo convertBankInfo(BankInformation bankInformation){
        return BankInfo.builder()
                .branchCode(bankInformation.getBranchCode())
                .branchName(bankInformation.getBranchName())
                .routingNumber(bankInformation.getRoutingNumber())
                .branchAddress(convertAddress(bankInformation.getBranchAddress()))
                .build();
    }
    public ContactDetails convertContactDetails(Contact contact){
        return ContactDetails.builder()
                .workPhone(contact.getWorkPhone())
                .emailId(contact.getEmailId())
                .homePhone(contact.getHomePhone())
                .build();

    }
    public Contact convertContacts(ContactDetails contactDetails){
        return Contact.builder()
                .emailId(contactDetails.getEmailId())
                .workPhone(contactDetails.getWorkPhone())
                .homePhone(contactDetails.getHomePhone())
                .build();
    }
    public TransactionDetails convertTransactionDetails(Transaction transaction){
        return TransactionDetails.builder()
                .accountNumber(transaction.getAccountNumber())
                .txAmount(transaction.getTxAmount())
                .txType(transaction.getTxType())
                .txDateTime(transaction.getTxDateTime())
                .build();

    }
public TransactionDetails convertTransaction(TransactionDetails transactionDetails, long accountNumber, String txtType){
        return TransactionDetails.builder()
                .accountNumber(transactionDetails.getAccountNumber())
                .txType(transactionDetails.getTxType())
                .txAmount(transactionDetails.getTxAmount())
                .txDateTime(transactionDetails.getTxDateTime()).build();
}
}
