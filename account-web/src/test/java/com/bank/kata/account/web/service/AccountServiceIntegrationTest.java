package com.bank.kata.account.web.service;

import com.bank.kata.account.web.entity.Account;
import com.bank.kata.account.web.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql("/data.sql")
class AccountServiceIntegrationTest {


    @Autowired
    private AccountService accountService;


    @Test
    void withdrawalTest() {
        accountService.withdrawal(1L, 10);

        Account account = accountService.getAccounts()
                .stream().filter(acc -> acc.getId() == 1L)
                .findFirst().get();

        assertEquals(-10, account.getBalance());
    }

    @Test
    void withdrawalNotFoundAccountTest() {
        assertThrows(NotFoundException.class, () -> accountService.withdrawal(12L, 10));
    }

    @Test
    void depositTest() {
        accountService.deposit(1L, 10);

        Account account = accountService.getAccounts()
                .stream().filter(acc -> acc.getId() == 1L)
                .findFirst().get();

        assertEquals(10, account.getBalance());

    }


    @Test
    void depositNotFoundAccountTest() {
        assertThrows(NotFoundException.class, () -> accountService.deposit(12L, 10));
    }

}
