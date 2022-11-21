package com.coding.exercise.bankapp.service.helper;

import com.coding.exercise.bankapp.domain.*;
import com.coding.exercise.bankapp.model.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BankingServiceHelper {

    public CustomerDetails convertToCustomerDomain(Customer customer) {

        return CustomerDetails.builder()
                .firstName(customer.getFirstName())
                .middleName(customer.getMiddleName())
                .lastName(customer.getLastName())
                .customerNumber(customer.getCustomerNumber())
                .status(customer.getStatus())
                .contactDetails(convertTocontactDomain(customer.getContactDetails()))
                .customerAddress(convertToAddressDomain(customer.getCustomerAddress()))
                .build();
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
    public BankInfo convertToBankInfoEntity(BankInformation bankInformation) {

        return BankInfo.builder()
                .branchCode(bankInformation.getBranchCode())
                .branchName(bankInformation.getBranchName())
                .routingNumber(bankInformation.getRoutingNumber())
                .branchAddress(convertToAddressEntity(bankInformation.getBranchAddress()))
                .build();
    }

    public Customer convertoCustomerEntity(CustomerDetails customerDetails) {
        return Customer.builder()
                .firstName(customerDetails.getFirstName())
                .lastName(customerDetails.getLastName())
                .middleName(customerDetails.getMiddleName())
                .customerNumber(customerDetails.getCustomerNumber())
                .status(customerDetails.getStatus())
                .contactDetails(convertTocontactEntity(customerDetails.getContactDetails()))
                .customerAddress(convertToAddressEntity(customerDetails.getCustomerAddress()))
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

    Account convertoAccountEntity(AccountInformation accountInformation) {
        return Account.builder()
                .accountBalance(accountInformation.getAccountBalance())
                .accountNumber(accountInformation.getAccountNumber())
                .accountStatus(accountInformation.getAccountStatus())
                .accountType(accountInformation.getAccountType())
                .bankInformation(convertBankInfoEntity(accountInformation.getBankInformation()))
                .build();
    }

    public AddressDetails convertToAddressDomain(Address address) {
        return AddressDetails.builder()
                .zip(address.getZip())
                .city(address.getCity())
                .state(address.getCity())
                .country(address.getCountry())
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .build();
    }

    public Address convertToAddressEntity(AddressDetails addressDetails) {
        return Address.builder()
                .city(addressDetails.getCity())
                .zip(addressDetails.getZip())
                .city(addressDetails.getCity())
                .country(addressDetails.getCountry())
                .address1(addressDetails.getAddress1())
                .address2(addressDetails.getAddress2())
                .build();
    }

    public ContactDetails convertTocontactDomain(Contact contact) {
        return ContactDetails.builder()
                .emailId(contact.getEmailId())
                .homePhone(contact.getHomePhone())
                .workPhone(contact.getWorkPhone())
                .build();
    }

    public Contact convertTocontactEntity(ContactDetails contactDetails) {
        return Contact.builder()
                .workPhone(contactDetails.getWorkPhone())
                .homePhone(contactDetails.getHomePhone())
                .emailId(contactDetails.getEmailId())
                .build();
    }

    public BankInformation convertTobankDomain(BankInfo bankInfo) {
        return BankInformation.builder()
                .branchName(bankInfo.getBranchName())
                .routingNumber(bankInfo.getRoutingNumber())
                .branchCode(bankInfo.getBranchCode())
                .branchAddress(convertToAddressDomain(bankInfo.getBranchAddress()))
                .build();
    }

    public BankInfo convertBankInfoEntity(BankInformation bankInformation) {
        return BankInfo.builder()
                .routingNumber(bankInformation.getRoutingNumber())
                .branchCode(bankInformation.getBranchCode())
                .branchName(bankInformation.getBranchName())
                .branchAddress(convertToAddressEntity(bankInformation.getBranchAddress()))
                .build();
    }

    public Transaction convertTotransactionDomain(TransactionDetails transactionDetails) {
        return Transaction.builder()
                .accountNumber(transactionDetails.getAccountNumber())
                .txAmount(transactionDetails.getTxAmount())
                .txType(transactionDetails.getTxType())
                .build();

    }

    public Transaction createTransaction(TransferDetails transferDetails, Long accountNumber, String txType) {

        return Transaction.builder()
                .accountNumber(accountNumber)
                .txAmount(transferDetails.getTransferAmount())
                .txType(txType)
                .txDateTime(new Date())
                .build();
    }
}
