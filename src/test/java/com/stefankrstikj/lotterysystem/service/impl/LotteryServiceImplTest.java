package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.LotteryStatus;
import com.stefankrstikj.lotterysystem.repository.LotteryRepository;
import com.stefankrstikj.lotterysystem.service.LotteryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
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
    void save() {
        // given
        when(repository.save(any())).thenReturn(createDummyLotteryWithId());
        Lottery lottery = createDummyLotteryWithoutId();

        // when
        Lottery actual = lotteryService.save(lottery);

        // then
        assertEquals(lottery.getDate(), actual.getDate());
        assertEquals(lottery.getWinningBallot().getUuid(), actual.getWinningBallot().getUuid());
    }

    @Test
    void findLotteryByDate() {
        // given
        Lottery lottery = createDummyLotteryWithId();
        when(repository.findByDateAndLotteryStatus(DATE, LotteryStatus.CLOSED)).thenReturn(Optional.of(lottery));

        // when
        Lottery actual = lotteryService.getLotteryForDate(DATE);

        // then
        assertEquals(lottery.getDate(), actual.getDate());
        assertEquals(lottery.getWinningBallot().getUuid(), actual.getWinningBallot().getUuid());
    }

    @Test
    void findLotteryByDateThrowsException() {
        // given
        when(repository.findByDateAndLotteryStatus(eq(DATE), any())).thenReturn(Optional.empty());

        // then
        assertThrows(EntityNotFoundException.class, () ->
                lotteryService.getLotteryForDate(DATE));
    }

    @Test
    void isLotteryActiveReturnsTrue() {
        // given
        when(repository.findByDateAndLotteryStatus(any(), any())).thenReturn(Optional.of(createDummyLotteryWithId()));

        // when
        boolean actual = lotteryService.isLotteryActive();

        // then
        assertTrue(actual);
    }

    @Test
    void isLotteryActiveReturnsFalse() {
        // given
        when(repository.findByDateAndLotteryStatus(eq(DATE), any())).thenReturn(Optional.empty());

        // when
        boolean actual = lotteryService.isLotteryActive();

        // then
        assertFalse(actual);
    }
}
