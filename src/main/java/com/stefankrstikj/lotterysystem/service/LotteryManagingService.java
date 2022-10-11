package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.model.response.LotteryBallotResponse;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface LotteryManagingService {
    LotteryBallotResponse createLotteryBallot();

    LotteryBallotResponse getLotteryBallotByUUID(UUID uuid);

    Optional<UUID> drawWinner();

    LotteryResponse startNewLottery();

    LotteryResponse getLotteryForDate(LocalDate date);

    LotteryResponse getOngoingLottery();
}
