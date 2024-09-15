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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void operation(@RequestBody TransferDto transfer) { // TODO: 14.09.2024 validation
        transferService.operation(transfer);
    }

    @GetMapping("/{id}")
    public TransferDto findById(@PathVariable Long id) {
        return transferService.findById(id);
    }

}
