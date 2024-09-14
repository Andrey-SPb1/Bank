package com.bank.mapper;

import com.bank.database.entity.Wallet;
import com.bank.dto.WalletDto;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper implements Mapper<Wallet, WalletDto> {

    @Override
    public WalletDto mapDto(Wallet obj) {
        return new WalletDto(obj.getBalance());
    }

    @Override
    public Wallet map(WalletDto obj) {
        return Wallet.builder()
                .balance(obj.balance())
                .build();
    }
}
