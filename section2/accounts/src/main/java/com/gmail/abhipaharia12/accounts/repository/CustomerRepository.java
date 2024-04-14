package com.gmail.abhipaharia12.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.abhipaharia12.accounts.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Optional<Customer> findByMobileNumber(String mobileNumber);
}
