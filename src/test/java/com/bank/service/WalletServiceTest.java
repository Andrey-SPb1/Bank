package com.bank.service;

import com.bank.IntegrationTestBase;
import com.bank.database.entity.Transfer;
import com.bank.database.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class WalletServiceTest extends IntegrationTestBase {

    private final TransferRepository transferRepository;

    @Test
    void checkFindById() {
        Optional<Transfer> transferOptional = transferRepository.findById(1L);
        assertTrue(transferOptional.isPresent());
        transferOptional.ifPresent(transfer -> assertEquals(150, transfer.getAmount()));
    }
}