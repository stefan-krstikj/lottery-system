package com.stefankrstikj.lotterysystem.mapper;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.response.LotteryBallotResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.BALLOT_UUID;
import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.createDummyLotteryWithId;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LotteryBallotMapperTest {
    private LotteryBallotMapper lotteryBallotMapper;

    @BeforeEach
    void setUp() {
        lotteryBallotMapper = new LotteryBallotMapper();
    }

    @AfterEach
    void tearDown() {
        lotteryBallotMapper = null;
    }

    @Test
    void entityToResponse() {
        LotteryBallot lotteryBallot = new LotteryBallot(createDummyLotteryWithId(), new User(), BALLOT_UUID);

        LotteryBallotResponse actual = lotteryBallotMapper.entityToResponse(lotteryBallot);

        assertEquals(lotteryBallot.getLottery().getId(), actual.getLotteryId());
        assertEquals(lotteryBallot.getLottery().getDate(), actual.getDate());
        assertEquals(lotteryBallot.getUuid(), actual.getUuid());
    }
}
