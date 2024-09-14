package com.bank.dto;

import com.bank.database.entity.OperationType;

public record TransferDto(Long walletId, Integer amount, OperationType operationType) {
}
