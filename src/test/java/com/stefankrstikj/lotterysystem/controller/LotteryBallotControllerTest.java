package com.stefankrstikj.lotterysystem.controller;

import com.stefankrstikj.lotterysystem.model.response.LotteryBallotResponse;
import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.BALLOT_UUID;
import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.createDummyLotteryBallotResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class LotteryBallotControllerTest {
    private LotteryBallotController lotteryBallotController;
    @Mock
    private LotteryManagingService lotteryManagingService;

    @BeforeEach
    void setUp() {
        lotteryBallotController = new LotteryBallotController(lotteryManagingService);
    }

    @AfterEach
    void tearDown() {
        lotteryBallotController = null;
    }

    @Test
    void getBallotByUUID() {
        when(lotteryManagingService.getLotteryBallotByUUID(BALLOT_UUID)).thenReturn(createDummyLotteryBallotResponse());

        LotteryBallotResponse actual = lotteryBallotController.getBallotByUUID(BALLOT_UUID);

        assertNotNull(actual);
        assertNotNull(actual.getUuid());
        assertNotNull(actual.getDate());
        assertNotNull(actual.getLotteryId());
    }

    @Test
    void getAllBallots() {
        when(lotteryManagingService.getAllBallots())
                .thenReturn(List.of(createDummyLotteryBallotResponse(), createDummyLotteryBallotResponse()));

        List<LotteryBallotResponse> response = lotteryBallotController.getAllBallots();

        assertEquals(2, response.size());
        assertNotNull(response.get(0).getUuid());
        assertNotNull(response.get(0).getDate());
        assertNotNull(response.get(0).getLotteryId());
        assertNotNull(response.get(1).getUuid());
        assertNotNull(response.get(1).getDate());
        assertNotNull(response.get(1).getLotteryId());
    }

    @Test
    void createBallot() {
        when(lotteryManagingService.createLotteryBallot()).thenReturn(createDummyLotteryBallotResponse());

        LotteryBallotResponse actual = lotteryBallotController.createBallot();

        assertNotNull(actual);
        assertNotNull(actual.getUuid());
        assertNotNull(actual.getDate());
        assertNotNull(actual.getLotteryId());
    }
}
