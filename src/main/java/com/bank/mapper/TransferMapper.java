package com.bank.mapper;

import com.bank.database.entity.Transfer;
import com.bank.database.entity.Wallet;
import com.bank.dto.TransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferMapper implements Mapper<Transfer, TransferDto> {

    @Override
    public TransferDto mapToDto(Transfer obj) {
        return new TransferDto(
                obj.getWallet().getId(),
                obj.getAmount(),
                obj.getOperationType());
    }

    @Override
    public Transfer mapToEntity(TransferDto obj) {
        return Transfer.builder()
                .amount(obj.amount())
                .wallet(Wallet.builder().id(obj.walletId()).build())
                .operationType(obj.operationType())
                .build();
    }
}
