package com.bank.kata.account.web.rest;

import com.bank.kata.account.web.dto.OperationDto;
import com.bank.kata.account.web.entity.Account;
import com.bank.kata.account.web.entity.Operation;
import com.bank.kata.account.web.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController()
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountResources {

    private final AccountService accountService;

    @GetMapping
    public Set<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    public Set<Operation> getOpperation(@PathVariable long id) {
        return accountService.getOperations(id);
    }


    @PostMapping("/{id}/deposit")
    public void withdrawal(@PathVariable long id, @RequestBody OperationDto operationVm) {
        accountService.deposit(id, operationVm.getAmount());
    }

    @PostMapping("/{id}/withdrawal")
    public void deposit(@PathVariable long id, @RequestBody OperationDto operationVm) {
        accountService.withdrawal(id, operationVm.getAmount());
    }

}
