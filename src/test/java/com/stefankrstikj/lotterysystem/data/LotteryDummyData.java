package com.stefankrstikj.lotterysystem.data;

import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.response.LotteryBallotResponse;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;

import java.time.LocalDate;
import java.util.UUID;

import static com.stefankrstikj.lotterysystem.data.UserDummyData.createDummyUser;

public class LotteryDummyData {
    public static final LocalDate DATE = LocalDate.of(2022, 9, 25);
    public static final UUID BALLOT_UUID = UUID.fromString("1f84e904-647a-4a11-98e5-d4449d5f7b4b");
    public static final long LOTTERY_ID = 0L;

    public static Lottery createDummyLotteryWithoutId() {
        Lottery lottery = new Lottery(DATE);
        LotteryBallot winningBallot = new LotteryBallot(lottery, createDummyUser(), BALLOT_UUID);
        lottery.setWinningBallot(winningBallot);
        return lottery;
    }

    public static Lottery createDummyLotteryWithId() {
        Lottery lottery = createDummyLotteryWithoutId();
        lottery.setId(LOTTERY_ID);
        return lottery;
    }

    public static LotteryResponse createDummyLotteryResponse() {
        return new LotteryResponse(LOTTERY_ID, DATE, BALLOT_UUID.toString());
    }

    public static LotteryBallotResponse createDummyLotteryBallotResponse() {
        return new LotteryBallotResponse(BALLOT_UUID, LOTTERY_ID, DATE, false);
    }

    public static LotteryBallot createDummyLotteryBallot() {
        return new LotteryBallot(createDummyLotteryWithId(), createDummyUser(), BALLOT_UUID);
    }
}
