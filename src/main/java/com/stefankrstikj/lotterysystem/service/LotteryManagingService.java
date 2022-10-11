package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface LotteryManagingService {
    UUID createLotteryBallot();

    Optional<UUID> drawWinner();

    LotteryResponse startNewLottery();

    LotteryResponse getLotteryForDate(LocalDate date);

    LotteryResponse getOngoingLottery();
}
