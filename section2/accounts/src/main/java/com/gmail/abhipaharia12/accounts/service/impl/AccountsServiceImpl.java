package com.gmail.abhipaharia12.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.gmail.abhipaharia12.accounts.constants.AccountsConstants;
import com.gmail.abhipaharia12.accounts.dto.AccountsDto;
import com.gmail.abhipaharia12.accounts.dto.CustomerDto;
import com.gmail.abhipaharia12.accounts.entity.Accounts;
import com.gmail.abhipaharia12.accounts.entity.Customer;
import com.gmail.abhipaharia12.accounts.exception.CustomerAlreadyExistsException;
import com.gmail.abhipaharia12.accounts.exception.ResourceNotFoundException;
import com.gmail.abhipaharia12.accounts.mapper.AccountsMapper;
import com.gmail.abhipaharia12.accounts.mapper.CustomerMapper;
import com.gmail.abhipaharia12.accounts.repository.AccountsRepository;
import com.gmail.abhipaharia12.accounts.repository.CustomerRepository;
import com.gmail.abhipaharia12.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;

    // // No need to write constructor because of @AllArgsConstructor. since it is only constructor, no need of @Autowired as spring will automatically will do it.
    // @Autowired
    // public AccountsServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository){
    //     this.accountsRepository = accountsRepository;
    //     this.customerRepository = customerRepository;
    // }


    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        Accounts accounts = createNewAccount(savedCustomer);
        Accounts savedAccounts = accountsRepository.save(accounts);
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("anonymous");
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)  
       );

       Accounts accounts =accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
            () -> new ResourceNotFoundException("customer", "mobileNumber", customer.getCustomerId().toString())  
       );
       CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
       customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
       return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
