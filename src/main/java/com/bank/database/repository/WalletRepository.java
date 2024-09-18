package com.bank.database.repository;

import com.bank.database.entity.Wallet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select w.balance from Wallet w " +
            "where w.id = :walletId")
    Optional<Integer> getBalanceById(Long walletId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Wallet " +
            "set balance=:balance where id=:walletId")
    void setBalanceById(Long walletId, Integer balance);
}