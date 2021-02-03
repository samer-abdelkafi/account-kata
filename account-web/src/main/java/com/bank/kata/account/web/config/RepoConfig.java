package com.bank.kata.account.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Configuration
@EnableJpaRepositories(basePackages = {"com.bank.kata.account.web.repo"})
@EnableTransactionManagement
public class RepoConfig {

    public RepoConfig() {
        log.info("config repo");
    }

}
