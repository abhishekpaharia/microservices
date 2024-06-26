package com.gmail.abhipaharia12.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.gmail.abhipaharia12.accounts.entity.Accounts;

import jakarta.transaction.Transactional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    public Optional<Accounts> findByCustomerId(long customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
