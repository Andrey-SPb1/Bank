package com.bank.service;

import com.bank.dto.WalletDto;

import java.util.Optional;

public interface WalletService {

    Optional<WalletDto> findById(Long id);

}
