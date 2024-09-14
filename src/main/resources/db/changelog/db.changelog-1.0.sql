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
    wallet_id     INT REFERENCES wallets (id),
    amount        INT NOT NULL,
    operationType VARCHAR(32),
    createdAt     TIMESTAMP
);
