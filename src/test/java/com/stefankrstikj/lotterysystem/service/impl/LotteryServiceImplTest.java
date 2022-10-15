package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.exception.NotFoundException;
import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.LotteryStatus;
import com.stefankrstikj.lotterysystem.repository.LotteryRepository;
import com.stefankrstikj.lotterysystem.service.LotteryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class LotteryServiceImplTest {

    private LotteryService lotteryService;
    @Mock
    private LotteryRepository repository;

    @BeforeEach
    void setUp() {
        lotteryService = new LotteryServiceImpl(repository);
    }

    @AfterEach
    void tearDown() {
        lotteryService = null;
    }

    @Test
    void saveSavesLottery() {
        when(repository.save(any())).thenReturn(createDummyLotteryWithId());
        Lottery lottery = createDummyLotteryWithoutId();

        Lottery actual = lotteryService.save(lottery);

        assertEquals(lottery.getDate(), actual.getDate());
        assertEquals(lottery.getWinningBallot().getUuid(), actual.getWinningBallot().getUuid());
    }

    @Test
    void findLotteryByDateReturnsLottery() {
        Lottery lottery = createDummyLotteryWithId();
        when(repository.findByDateAndLotteryStatus(DATE, LotteryStatus.CLOSED)).thenReturn(Optional.of(lottery));

        Lottery actual = lotteryService.getLotteryForDate(DATE);

        assertEquals(lottery.getDate(), actual.getDate());
        assertEquals(lottery.getWinningBallot().getUuid(), actual.getWinningBallot().getUuid());
    }

    @Test
    void findLotteryByDateThrowsNotFoundException() {
        when(repository.findByDateAndLotteryStatus(eq(DATE), any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                lotteryService.getLotteryForDate(DATE));
    }

    @Test
    void getOngoingLotteryReturnsLottery() {
        Lottery lottery = createDummyLotteryWithId();
        when(repository.findByLotteryStatus(LotteryStatus.OPEN)).thenReturn(Optional.of(lottery));

        Lottery actual = lotteryService.getOngoingLottery();

        assertEquals(lottery.getDate(), actual.getDate());
        assertEquals(lottery.getWinningBallot().getUuid(), actual.getWinningBallot().getUuid());
    }

    @Test
    void getOngoingLotteryThrowsNotFoundException() {
        when(repository.findByLotteryStatus(LotteryStatus.OPEN)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                lotteryService.getOngoingLottery());
    }

    @Test
    void isLotteryActiveReturnsTrue() {
        when(repository.findByDateAndLotteryStatus(any(), any())).thenReturn(Optional.of(createDummyLotteryWithId()));

        boolean actual = lotteryService.isLotteryActive();

        assertTrue(actual);
    }

    @Test
    void isLotteryActiveReturnsFalse() {
        when(repository.findByDateAndLotteryStatus(eq(DATE), any())).thenReturn(Optional.empty());

        boolean actual = lotteryService.isLotteryActive();

        assertFalse(actual);
    }
}
