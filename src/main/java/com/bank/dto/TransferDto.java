package com.bank.dto;

import com.bank.database.entity.OperationType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TransferDto(
        @NotNull
        Long walletId,
        @Min(1) @Max(10_000_000)
        Integer amount,
        @NotNull
        OperationType operationType) {
}
