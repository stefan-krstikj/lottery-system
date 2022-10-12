package com.stefankrstikj.lotterysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.DATE;
import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.createDummyLotteryResponse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is not a full integration test as we are using a Mock Service.
 * These tests confirm that the HTTP Binding works as expected.
 */
@WebMvcTest(controllers = LotteryController.class)
class LotteryControllerIT {

    @MockBean
    private LotteryManagingService lotteryManagingService;

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @AfterEach
    void tearDown() {
        mockMvc = null;
    }

    @Test
    void getOngoingLottery() throws Exception {
        when(lotteryManagingService.getOngoingLottery()).thenReturn(createDummyLotteryResponse());

        mockMvc.perform(get("/api/lottery"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.winner").exists())
                .andDo(print());
    }

    @Test
    void getLotteryForDate() throws Exception {
        when(lotteryManagingService.getLotteryForDate(DATE)).thenReturn(createDummyLotteryResponse());
        mockMvc.perform(get("/api/lottery/{date}", DATE.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.winner").exists())
                .andDo(print());
    }
}
