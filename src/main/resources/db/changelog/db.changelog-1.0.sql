--liquibase formatted sql

--changeset andrey:1
CREATE TABLE IF NOT EXISTS wallets
(
    id      BIGSERIAL PRIMARY KEY,
    balance INT NOT NULL
);

--changeset andrey:2
CREATE TABLE IF NOT EXISTS transfers
(
    id            BIGSERIAL PRIMARY KEY,
    wallet_id     BIGINT REFERENCES wallets (id),
    amount        INT NOT NULL,
    operation_type VARCHAR(32) CHECK (operation_type in ('DEPOSIT','WITHDRAW')),
    created_at     TIMESTAMP
);
