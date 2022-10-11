package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;

import java.util.UUID;

public interface LotteryBallotService {
    LotteryBallot create(LotteryBallot lotteryBallot);

    LotteryBallot findByUUID(UUID uuid);
}
