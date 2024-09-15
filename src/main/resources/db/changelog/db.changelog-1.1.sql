--liquibase formatted sql

--changeset andrey:1
INSERT INTO wallets(balance)
VALUES ('120'),
       ('1000'),
       ('0');

--changeset andrey:2
INSERT INTO transfers(amount, wallet_id, operation_type)
VALUES ('150', 1, 'DEPOSIT'),
       ('1000', 2, 'DEPOSIT'),
       ('100', 1, 'WITHDRAW');