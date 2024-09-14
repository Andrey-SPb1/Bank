package com.bank.controller;

import com.bank.dto.TransferDto;
import com.bank.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TransferDto deposit(@RequestBody TransferDto transfer) {
        return transferService.deposit(transfer);
    }

}
