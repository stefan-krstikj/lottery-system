package com.stefankrstikj.lotterysystem.mapper;

import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LotteryMapperTest {
    private LotteryMapper lotteryMapper;

    @BeforeEach
    void setUp() {
        lotteryMapper = new LotteryMapper();
    }

    @AfterEach
    void tearDown() {
        lotteryMapper = null;
    }

    @Test
    void entityToResponse() {
        // given
        Lottery lottery = new Lottery(LocalDate.of(2022, 9, 25));
        lottery.setId(0L);
        LotteryResponse expected = new LotteryResponse(0L, LocalDate.of(2022, 9, 25));

        // when
        LotteryResponse actual = lotteryMapper.entityToResponse(lottery);

        // then
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getId(), actual.getId());
    }
}
