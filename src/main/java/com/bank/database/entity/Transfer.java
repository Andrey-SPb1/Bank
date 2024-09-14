package com.bank.database.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transfers")
public class Transfer extends Audit{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column(nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;
}
