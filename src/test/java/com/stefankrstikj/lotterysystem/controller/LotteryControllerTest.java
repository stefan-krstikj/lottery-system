package com.stefankrstikj.lotterysystem.controller;

import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;
import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.DATE;
import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.createDummyLotteryResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class LotteryControllerTest {
    private LotteryController lotteryController;

    @Mock
    LotteryManagingService lotteryManagingService;
    @BeforeEach
    void setUp() {
        lotteryController = new LotteryController(lotteryManagingService);
    }

    @AfterEach
    void tearDown() {
        lotteryController = null;
    }

    @Test
    void getOngoingLottery() {
        when(lotteryManagingService.getOngoingLottery()).thenReturn(createDummyLotteryResponse());

        LotteryResponse actual = lotteryController.getOngoingLottery();

        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertNotNull(actual.getDate());
    }

    @Test
    void getLotteryForDate() {
        when(lotteryManagingService.getLotteryForDate(DATE)).thenReturn(createDummyLotteryResponse());

        LotteryResponse actual = lotteryController.getLotteryForDate(DATE);

        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertNotNull(actual.getDate());
    }
}
