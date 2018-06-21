package com.ultimatesoftware.banking.transactions.domain.eventhandlers;

import com.ultimatesoftware.banking.account.common.events.*;
import com.ultimatesoftware.banking.transactions.domain.models.BankTransaction;
import com.ultimatesoftware.banking.transactions.domain.models.TransactionStatus;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ultimatesoftware.banking.transactions.service.repositories.BankTransactionRepository;

import java.util.UUID;

public class AccountEventHandlers {
    private static final Logger LOG = LoggerFactory.getLogger(AccountEventHandlers.class);

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    public AccountEventHandlers() {}

    @EventHandler
    public void on(AccountCreditedEvent event) {
        LOG.info("Account Credited {}", event.getId());
        updateTransaction(event);
    }

    @EventHandler
    public void on(AccountDebitedEvent event) {
        LOG.info("Account Debited {}", event.getId());
        updateTransaction(event);
    }

    @EventHandler
    public void on(AccountOverdraftedEvent event) {
        LOG.info("Account Overdraft {}", event.getId());
        updateTransaction(event);
    }

    @EventHandler
    public void on(TransactionFailedEvent event) {
        BankTransaction transaction = bankTransactionRepository.findOne(UUID.fromString(event.getId()));
        if(transaction != null) {
            transaction.setStatus(TransactionStatus.FAILED);
        }
        bankTransactionRepository.save(transaction);
    }

    private void updateTransaction(AccountTransactionEvent event) {
        BankTransaction transaction = bankTransactionRepository.findOne(UUID.fromString(event.getTransactionId()));
        if(transaction != null) {
            transaction.setStatus(TransactionStatus.SUCCESSFUL);
        }
        bankTransactionRepository.save(transaction);
    }
}