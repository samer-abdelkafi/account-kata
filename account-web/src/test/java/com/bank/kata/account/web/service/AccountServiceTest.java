package com.bank.kata.account.web.service;

import com.bank.kata.account.web.entity.Account;
import com.bank.kata.account.web.entity.Operation;
import com.bank.kata.account.web.exception.NotFoundException;
import com.bank.kata.account.web.repo.AccountRepo;
import com.bank.kata.account.web.repo.OperationRepo;
import com.bank.kata.account.web.dto.OperationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {


    @Mock
    private AccountRepo accountRepo;

    @Mock
    private OperationRepo operationRepo;

    @Captor
    ArgumentCaptor<Operation> operationArgumentCaptor;

    @InjectMocks
    private AccountService accountService;


    @Test
    void withdrawalTest() {

        Account account = new Account();
        account.setBalance(100);

        when(accountRepo.findById(1L))
                .thenReturn(Optional.of(account));

        accountService.withdrawal(1L, 10);

        Mockito.verify(operationRepo).save(operationArgumentCaptor.capture());

        Operation operationCaptorValue = operationArgumentCaptor.getValue();

        assertEquals(OperationType.DEBIT.name(), operationCaptorValue.getType());
        assertEquals(-10, operationCaptorValue.getAmount());
        assertEquals(90, operationCaptorValue.getAccount().getBalance());
    }


    @Test
    void withdrawalNotFoundAccountTest() {
        assertThrows(NotFoundException.class, () -> accountService.withdrawal(12L, 10));
    }


    @Test
    void depositTest() {

        Account account = new Account();
        account.setBalance(100);

        when(accountRepo.findById(1L))
                .thenReturn(Optional.of(account));

        accountService.deposit(1L, 20);

        Mockito.verify(operationRepo).save(operationArgumentCaptor.capture());

        Operation operationCaptorValue = operationArgumentCaptor.getValue();

        assertEquals(OperationType.CREDIT.name(), operationCaptorValue.getType());
        assertEquals(20, operationCaptorValue.getAmount());
        assertEquals(120, operationCaptorValue.getAccount().getBalance());
    }

    @Test
    void depositNotFoundAccountTest() {
        assertThrows(NotFoundException.class, () -> accountService.deposit(12L, 10));
    }

    @Test
    void getOperations() {

        Operation operation = new Operation();
        operation.setId(100L);
        operation.setType(OperationType.CREDIT.name());

        when(accountService.getOperations(1L)).thenReturn(Set.of(operation));

        Set<Operation> operations = accountService.getOperations(1L);

        assertEquals(operation, operations.stream().findFirst().get());
    }

    @Test
    void getAccounts() {

    }
}
