package com.bank.service.impl;

import com.bank.database.repository.WalletRepository;
import com.bank.dto.WalletDto;
import com.bank.mapper.WalletMapper;
import com.bank.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public Optional<WalletDto> findById(Long id) {
        return walletRepository.findById(id)
                .map(walletMapper::mapToDto);
    }
}
