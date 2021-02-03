package com.bank.kata.account.web.repo;

import com.bank.kata.account.web.entity.Account;
import com.bank.kata.account.web.entity.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OperationRepoIntegrationTest {


    @Autowired
    private OperationRepo operationRepo;


    @Test
    void getOperationByAccountIdTest(){

        Operation operation = new Operation();
        operation.setAmount(10);
        operation.setType("DEBIT");
        operation.setAccount(new Account());
        operation.getAccount().setId(1L);

        operationRepo.save(operation);

        Operation savedOperation = operationRepo.getOperationByAccount_Id(1L).stream()
                .findFirst().get();

        assertEquals(10, savedOperation.getAmount());

    }

}
