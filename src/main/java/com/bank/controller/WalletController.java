package com.bank.controller;

import com.bank.dto.WalletDto;
import com.bank.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/{id}")
    public WalletDto findById(@PathVariable("id") Long id) {
        return walletService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
