package com.ultimatesoftware.banking.account.cmd.domain.commands;

import java.util.UUID;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class StartTransferWithdrawCommand extends TransactionCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private double amount;

    public StartTransferWithdrawCommand(UUID id, double amount, String transactionId) {
        super(transactionId);
        this.id = id;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }
}
