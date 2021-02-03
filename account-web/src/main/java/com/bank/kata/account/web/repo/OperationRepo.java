package com.bank.kata.account.web.repo;

import com.bank.kata.account.web.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OperationRepo extends JpaRepository<Operation, Long> {

    Set<Operation> getOperationByAccount_Id(long accountId);

}
