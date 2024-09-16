package com.bank.controller;

import com.bank.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class WalletControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void checkFindById() throws Exception {
        mockMvc.perform(get("/api/v1/wallet/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void checkFindByWrongId() throws Exception {
        mockMvc.perform(get("/api/v1/wallet/10"))
                .andExpect(status().isNotFound());
    }
}