package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;

import java.util.Optional;
import java.util.UUID;

public interface LotteryManagingService {
    UUID createLotteryBallot();

    boolean isWinningLotteryBallot(LotteryBallot lotteryBallot);

    Optional<UUID> drawWinner();

    LotteryResponse startNewLottery();

    LotteryResponse getCurrentLottery();
}
