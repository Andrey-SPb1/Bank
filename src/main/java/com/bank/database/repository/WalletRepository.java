package com.bank.database.repository;

import com.bank.database.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("select w.balance from Wallet w " +
            "where w.id = :walletId")
    Optional<Integer> getBalanceById(Long walletId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Wallet " +
            "set balance=:balance where id=:walletId")
    void setBalanceById(Long walletId, Integer balance);

}