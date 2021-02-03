package com.bank.kata.account.web.repo;

import com.bank.kata.account.web.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepoIntegrationTest {


    @Autowired
            
    AccountRepo accountRepo;


    @Test
    void saveAccountTest() {

        List<Account> result = accountRepo.findAll();
        assertEquals(2, result.size());

        Account currentAccount = result.stream()
                .filter(account -> account.getId() == 1)
                .findFirst()
                .get();


        assertEquals("Current account", currentAccount.getName());
        assertEquals(0, currentAccount.getBalance());
    }

}
