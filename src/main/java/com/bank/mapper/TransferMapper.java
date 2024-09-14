package com.bank.mapper;

import com.bank.database.entity.Transfer;
import com.bank.database.entity.Wallet;
import com.bank.database.repository.WalletRepository;
import com.bank.dto.TransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransferMapper implements Mapper<Transfer, TransferDto> {

    private final WalletRepository walletRepository;

    @Override
    public TransferDto mapDto(Transfer obj) {
        return new TransferDto(obj.getWallet().getId(),
                obj.getAmount(),
                obj.getOperationType());
    }

    @Override
    public Transfer map(TransferDto obj) {
        Optional<Wallet> wallet = walletRepository.findById(obj.walletId());
        return Transfer.builder()
                .amount(obj.amount())
                .wallet(wallet.orElseThrow())
                .operationType(obj.operationType())
                .build();
    }


}
