package com.stefankrstikj.lotterysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefankrstikj.lotterysystem.model.response.LotteryBallotResponse;
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

import java.util.ArrayList;
import java.util.List;

import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.BALLOT_UUID;
import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.createDummyLotteryBallotResponse;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is not a full integration test as we are using a Mock Service.
 * These tests confirm that the HTTP Binding works as expected.
 */
@WebMvcTest(controllers = LotteryBallotController.class)
class LotteryBallotControllerIT {

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
    void getBallotByUUID() throws Exception {
        when(lotteryManagingService.getLotteryBallotByUUID(any())).thenReturn(createDummyLotteryBallotResponse());

        mockMvc.perform(get("/api/lottery/ballots/{uuid}", BALLOT_UUID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").exists())
                .andExpect(jsonPath("$.lotteryId").exists())
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.winner").exists())
                .andDo(print());
    }

    @Test
    void getAllBallots() throws Exception {
        List<LotteryBallotResponse> ballots = List.of(
                createDummyLotteryBallotResponse(),
                createDummyLotteryBallotResponse());
        when(lotteryManagingService.getAllBallots()).thenReturn(ballots);

        mockMvc.perform(get("/api/lottery/ballots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].uuid").exists())
                .andExpect(jsonPath("$[0].lotteryId").exists())
                .andExpect(jsonPath("$[0].date").exists())
                .andExpect(jsonPath("$[0].winner").exists())
                .andExpect(jsonPath("$[1].uuid").exists())
                .andExpect(jsonPath("$[1].lotteryId").exists())
                .andExpect(jsonPath("$[1].date").exists())
                .andExpect(jsonPath("$[1].winner").exists())
                .andDo(print());
    }

    @Test
    void createBallot() throws Exception {
        when(lotteryManagingService.createLotteryBallot()).thenReturn(createDummyLotteryBallotResponse());

        mockMvc.perform(post("/api/lottery/ballots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").exists())
                .andExpect(jsonPath("$.lotteryId").exists())
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.winner").exists())
                .andDo(print());
    }
}
