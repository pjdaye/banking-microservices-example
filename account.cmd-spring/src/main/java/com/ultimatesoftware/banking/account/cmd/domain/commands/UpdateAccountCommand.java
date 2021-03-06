package com.ultimatesoftware.banking.account.cmd.domain.commands;

import com.ultimatesoftware.banking.account.cmd.domain.models.AccountUpdateDto;

import java.util.UUID;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateAccountCommand implements ICommand {
    @TargetAggregateIdentifier
    private UUID id;
    private String customerId;

    public UpdateAccountCommand(UUID id, String customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public UpdateAccountCommand(UUID id, AccountUpdateDto accountUpdateDto) {
        this.id = id;
        this.customerId = accountUpdateDto.getCustomerId();
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }
}
