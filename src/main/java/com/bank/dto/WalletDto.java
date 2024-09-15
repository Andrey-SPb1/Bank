package com.bank.dto;

import jakarta.validation.constraints.Min;

public record WalletDto(
        @Min(0)
        Integer balance) {
}
