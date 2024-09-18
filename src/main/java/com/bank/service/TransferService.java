package com.bank.service;

import com.bank.dto.TransferDto;

import java.util.Optional;

public interface TransferService {

    Optional<TransferDto> findById(Long id);

    void operation(TransferDto transfer);
}
