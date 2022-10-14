package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.response.LotteryBallotResponse;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This is a managing service for {@link LotteryService} and {@link LotteryBallotService}. Please use this service
 * to access any Lottery or LotteryBallot related logic
 */
public interface LotteryManagingService {
    LotteryBallotResponse createLotteryBallot();

    LotteryBallotResponse getLotteryBallotByUUID(UUID uuid);

    List<LotteryBallotResponse> getAllBallots();

    Optional<LotteryBallot> drawWinner();

    LotteryResponse startNewLottery();

    LotteryResponse getLotteryForDate(LocalDate date);

    LotteryResponse getOngoingLottery();
}
