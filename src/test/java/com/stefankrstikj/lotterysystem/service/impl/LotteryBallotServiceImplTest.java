package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.exception.NotFoundException;
import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.LotteryStatus;
import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.repository.LotteryBallotRepository;
import com.stefankrstikj.lotterysystem.service.LotteryBallotService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.BALLOT_UUID;
import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.createDummyLotteryBallot;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    void createCreatesBallot() {
        LotteryBallot lotteryBallot = createDummyLotteryBallot();
        when(lotteryBallotRepository.save(any())).thenReturn(lotteryBallot);

        LotteryBallot actual = lotteryBallotService.create(new LotteryBallot());

        assertEquals(lotteryBallot.getLottery().getId(), actual.getLottery().getId());
        assertEquals(lotteryBallot.getUuid(), actual.getUuid());
    }

    @Test
    void findByUUIDReturnsBallot() {
        LotteryBallot lotteryBallot = createDummyLotteryBallot();
        when(lotteryBallotRepository.findLotteryBallotByUuid(any())).thenReturn(Optional.of(lotteryBallot));

        LotteryBallot actual = lotteryBallotService.findByUUID(BALLOT_UUID);

        assertNotNull(actual);
        assertNotNull(actual.getUuid());
        assertNotNull(actual.getLottery());
    }

    @Test
    void findByUUIDThrowsNotFoundException() {
        when(lotteryBallotRepository.findLotteryBallotByUuid(BALLOT_UUID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                lotteryBallotService.findByUUID(BALLOT_UUID));
    }

    @Test
    void findAllByUserReturnsListOfBallots() {
        LotteryBallot dummyBallot = createDummyLotteryBallot();
        dummyBallot.setUuid(UUID.randomUUID());
        List<LotteryBallot> ballots = List.of(createDummyLotteryBallot(), dummyBallot);
        when(lotteryBallotRepository.findLotteryBallotsByParticipantAndLotteryLotteryStatus(any(), eq(LotteryStatus.OPEN)))
                .thenReturn(ballots);

        List<LotteryBallot> actual = lotteryBallotService.findAllByUser(new User());
        assertEquals(2, actual.size());
        assertNotNull(actual.get(0).getUuid());
        assertNotNull(actual.get(1).getUuid());
    }
}
