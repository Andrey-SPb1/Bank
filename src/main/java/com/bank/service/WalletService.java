package com.bank.service;

import com.bank.database.repository.WalletRepository;
import com.bank.dto.WalletDto;
import com.bank.mapper.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public WalletDto findById(Long id) {
        return walletRepository.findById(id)
                .map(walletMapper::mapToDto)
                .orElseThrow();
    }
}
