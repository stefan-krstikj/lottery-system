package com.stefankrstikj.lotterysystem.data;

import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.LotteryBallot;

import java.time.LocalDate;
import java.util.UUID;

public class LotteryDummyData {
    public static final LocalDate DATE = LocalDate.of(2022, 9, 25);
    public static final UUID BALLOT_UUID = UUID.fromString("1f84e904-647a-4a11-98e5-d4449d5f7b4b");

    public static Lottery createDummyLotteryWithoutId() {
        Lottery lottery = new Lottery(DATE);
        LotteryBallot winningBallot = new LotteryBallot(lottery, null, BALLOT_UUID);
        lottery.setWinningBallot(winningBallot);
        return lottery;
    }

    public static Lottery createDummyLotteryWithId() {
        Lottery lottery = createDummyLotteryWithoutId();
        lottery.setId(0L);
        return lottery;
    }
}
