package com.coding.exercise.bankapp.service.helper;

import com.coding.exercise.bankapp.domain.*;
import com.coding.exercise.bankapp.model.*;
import org.springframework.stereotype.Component;

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
    public Account convertToAccountEntity(AccountInformation accInfo) {

        return Account.builder()
                .accountType(accInfo.getAccountType())
                .accountBalance(accInfo.getAccountBalance())
                .accountNumber(accInfo.getAccountNumber())
                .accountStatus(accInfo.getAccountStatus())
                .bankInformation(convertToBankInfoEntity(accInfo.getBankInformation()))
                .build();
    }
public  BankInfo convertToBankInfoEntity(BankInformation bankInformation){
        return BankInfo.builder()
                .branchCode(bankInformation.getBranchCode())
                .branchName(bankInformation.getBranchName())
                .routingNumber(bankInformation.getRoutingNumber())
                .branchAddress(convertToAddressEntity(bankInformation.getBranchAddress()))
                .build();
}
    public Address convertToAddressEntity(AddressDetails addressDetails) {

        return Address.builder().address1(addressDetails.getAddress1())
                .address2(addressDetails.getAddress2())
                .city(addressDetails.getCity())
                .state(addressDetails.getState())
                .zip(addressDetails.getZip())
                .country(addressDetails.getCountry())
                .build();
    }

    public AccountInformation convertToAccountDomain(Account account) {

        return AccountInformation.builder()
                .accountType(account.getAccountType())
                .accountBalance(account.getAccountBalance())
                .accountNumber(account.getAccountNumber())
                .accountStatus(account.getAccountStatus())
                .bankInformation(convertToBankInfoDomain(account.getBankInformation()))
                .build();
    }
    public BankInformation convertToBankInfoDomain(BankInfo bankInfo) {

        return BankInformation.builder()
                .branchCode(bankInfo.getBranchCode())
                .branchName(bankInfo.getBranchName())
                .routingNumber(bankInfo.getRoutingNumber())
                .branchAddress(convertToAddressDomain(bankInfo.getBranchAddress()))
                .build();
    }
    public CustomerDetails convertToCustomerDomain(Customer customer) {

        return CustomerDetails.builder()
                .firstName(customer.getFirstName())
                .middleName(customer.getMiddleName())
                .lastName(customer.getLastName())
                .customerNumber(customer.getCustomerNumber())
                .status(customer.getStatus())
                .contactDetails(convertToContactDomain(customer.getContactDetails()))
                .customerAddress(convertToAddressDomain(customer.getCustomerAddress()))
                .build();
    }
    public ContactDetails convertToContactDomain(Contact contact) {

        return ContactDetails.builder()
                .emailId(contact.getEmailId())
                .homePhone(contact.getHomePhone())
                .workPhone(contact.getWorkPhone())
                .build();
    }
    public AddressDetails convertToAddressDomain(Address address) {

        return AddressDetails.builder().address1(address.getAddress1())
                .address2(address.getAddress2())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .country(address.getCountry())
                .build();
    }

}
