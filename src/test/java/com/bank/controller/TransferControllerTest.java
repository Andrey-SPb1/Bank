package com.bank.controller;

import com.bank.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class TransferControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void checkFindById() throws Exception {
        mockMvc.perform(get("/api/v1/transfer/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void checkFindByWrongId() throws Exception {
        mockMvc.perform(get("/api/v1/transfer/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    void checkOperationDeposit() throws Exception {
        mockMvc.perform(post("/api/v1/transfer").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "amount": "1000",
                                    "operationType": "DEPOSIT",
                                    "walletId": "3"
                                }
                                """))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    void checkOperationDepositWithWrongAmount() throws Exception {
        mockMvc.perform(post("/api/v1/transfer").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "amount": "-1000",
                                    "operationType": "DEPOSIT",
                                    "walletId": "3"
                                }
                                """))
                .andExpect(status().is4xxClientError());
    }
    @Test
    void checkOperationDepositWithWrongWalletId() throws Exception {
        mockMvc.perform(post("/api/v1/transfer").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "amount": "1000",
                                    "operationType": "DEPOSIT",
                                    "walletId": "10"
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    void checkOperationWithdraw() throws Exception {
        mockMvc.perform(post("/api/v1/transfer").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "amount": "1000",
                                    "operationType": "WITHDRAW",
                                    "walletId": "2"
                                }
                                """))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void checkOperationWithWrongOperationType() throws Exception {
        mockMvc.perform(post("/api/v1/transfer").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "amount": "1000",
                                    "operationType": "DEPOST",
                                    "walletId": "3"
                                }
                                """))
                .andExpect(status().is4xxClientError());
    }
}