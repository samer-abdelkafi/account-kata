package com.bank.kata.account.web.service;

import com.bank.kata.account.web.dto.OperationType;
import com.bank.kata.account.web.entity.Account;
import com.bank.kata.account.web.entity.Operation;
import com.bank.kata.account.web.exception.NotFoundException;
import com.bank.kata.account.web.exception.UnahandledException;
import com.bank.kata.account.web.repo.AccountRepo;
import com.bank.kata.account.web.repo.OperationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;

    private final OperationRepo operationRepo;

    @Transactional
    public void withdrawal(long accountId, double amount) {
        executeOperation(OperationType.DEBIT, accountId, -1 * amount);
    }

    @Transactional
    public void deposit(long accountId, double amount) {
        executeOperation(OperationType.CREDIT, accountId, amount);
    }

    private void executeOperation(OperationType operationType, long accountId, double amount) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(NotFoundException::new);

        account.setBalance(account.getBalance() + amount);

        Operation operation = new Operation();
        operation.setType(operationType.name());
        operation.setAmount(amount);
        operation.setDateOperation(new Date());
        operation.setAccount(account);
        operationRepo.save(operation);
    }


    public Set<Operation> getOperations(long accountId) {
        return operationRepo.getOperationByAccount_Id(accountId);
    }

    public Set<Account> getAccounts() {
        return new HashSet<>(accountRepo.findAll());
    }


    public Account getAccountById(long id) {
        return accountRepo.findById(id).orElseThrow(NotFoundException::new);
    }

}
