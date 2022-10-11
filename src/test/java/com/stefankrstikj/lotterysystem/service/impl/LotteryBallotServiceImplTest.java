package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.repository.LotteryBallotRepository;
import com.stefankrstikj.lotterysystem.service.LotteryBallotService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.BALLOT_UUID;
import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.createDummyLotteryWithId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class LotteryBallotServiceImplTest {

    private LotteryBallotService lotteryBallotService;

    @Mock
    private LotteryBallotRepository lotteryBallotRepository;

    @BeforeEach
    void setUp() {
        lotteryBallotService = new LotteryBallotServiceImpl(lotteryBallotRepository);
    }

    @AfterEach
    void tearDown() {
        lotteryBallotService = null;
    }

    @Test
    void create() {
        // given
        LotteryBallot lotteryBallot = new LotteryBallot(createDummyLotteryWithId(), new User(), BALLOT_UUID);
        when(lotteryBallotRepository.save(any())).thenReturn(lotteryBallot);

        // when
        LotteryBallot actual = lotteryBallotService.create(new LotteryBallot());

        // then
        assertEquals(lotteryBallot.getLottery().getId(), actual.getLottery().getId());
        assertEquals(lotteryBallot.getUuid(), actual.getUuid());
    }
}
