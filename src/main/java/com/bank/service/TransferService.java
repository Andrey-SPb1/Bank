package com.bank.service;

import com.bank.database.entity.OperationType;
import com.bank.database.entity.Wallet;
import com.bank.database.repository.TransferRepository;
import com.bank.database.repository.WalletRepository;
import com.bank.dto.TransferDto;
import com.bank.mapper.TransferMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;
    private final TransferMapper transferMapper;

    @Transactional(rollbackOn = Exception.class)
    public TransferDto deposit(TransferDto transfer) {
        if(transfer.operationType() != OperationType.DEPOSIT) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Wallet wallet = walletRepository.findById(transfer.walletId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Integer balance = wallet.getBalance();
        wallet.setBalance(balance + transfer.amount());

        return Optional.of(transfer)
                .map(transferMapper::map)
                .map(transferRepository::save)
                .map(transferMapper::mapDto)
                .orElseThrow();
    }

//    @Transactional
//    public TransferDto withdraw(TransferDto transferDto) {
//
//    }

}
