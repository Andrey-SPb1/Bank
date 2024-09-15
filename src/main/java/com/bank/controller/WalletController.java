package com.bank.controller;

import com.bank.dto.WalletDto;
import com.bank.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WalletDto findById(@PathVariable("id") Long id) {
        return walletService.findById(id);
    }

}
