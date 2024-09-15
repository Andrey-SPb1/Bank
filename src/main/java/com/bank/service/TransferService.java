package com.bank.service;

import com.bank.database.repository.TransferRepository;
import com.bank.database.repository.WalletRepository;
import com.bank.dto.TransferDto;
import com.bank.mapper.TransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferMapper transferMapper;
    private final WalletRepository walletRepository;
    private final TransferRepository transferRepository;
    private final Lock transferLock = new ReentrantLock();

    public TransferDto findById(Long id) {
        return transferRepository.findById(id)
                .map(transferMapper::mapToDto)
                .orElseThrow();
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void operation(TransferDto transfer) {
        switch (transfer.operationType()) {
            case DEPOSIT -> deposit(transfer);
            case WITHDRAW -> withdraw(transfer);
        }

        Optional.of(transfer)
                .map(transferMapper::mapToEntity)
                .map(transferRepository::save)
                .orElseThrow();
    }

    private void deposit(TransferDto transfer) {
        transferLock.lock();
        try {
            int balance = walletRepository.getBalanceById(transfer.walletId()).orElseThrow();
            int newBalance = balance - transfer.amount();
            walletRepository.setBalanceById(transfer.walletId(), newBalance);
        } finally {
            transferLock.unlock();
        }
    }

    private void withdraw(TransferDto transfer) {
        transferLock.lock();
        try {
            int balance = walletRepository.getBalanceById(transfer.walletId()).orElseThrow();
            int newBalance = balance - transfer.amount();
            System.out.println(balance + " and " + newBalance);
            if (newBalance < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
            } else {
                walletRepository.setBalanceById(transfer.walletId(), newBalance);
            }
        } finally {
            transferLock.unlock();
        }
    }

}
