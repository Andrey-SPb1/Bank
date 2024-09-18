package com.bank.service.impl;

import com.bank.database.repository.TransferRepository;
import com.bank.database.repository.WalletRepository;
import com.bank.dto.TransferDto;
import com.bank.mapper.TransferMapper;
import com.bank.profiling.Profiling;
import com.bank.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferMapper transferMapper;
    private final WalletRepository walletRepository;
    private final TransferRepository transferRepository;

    public Optional<TransferDto> findById(Long id) {
        return transferRepository.findById(id)
                .map(transferMapper::mapToDto);
    }

    @Profiling
    @Transactional
    public void operation(TransferDto transfer) {
        switch (transfer.operationType()) {
            case DEPOSIT -> deposit(transfer);
            case WITHDRAW -> withdraw(transfer);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid operation type");
        }

        Optional.of(transfer)
                .map(transferMapper::mapToEntity)
                .map(transferRepository::save)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    private void deposit(TransferDto transfer) {
        Long walletId = transfer.walletId();
        int balance = walletRepository.getBalanceById(walletId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        int newBalance = balance + transfer.amount();
        walletRepository.setBalanceById(walletId, newBalance);
    }

    private void withdraw(TransferDto transfer) {
        Long walletId = transfer.walletId();
        int balance = walletRepository.getBalanceById(walletId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        int newBalance = balance - transfer.amount();
        if (newBalance < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        } else {
            walletRepository.setBalanceById(walletId, newBalance);
        }
    }
}
