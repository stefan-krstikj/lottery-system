package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.User;

import java.util.List;
import java.util.UUID;

public interface LotteryBallotService {
    LotteryBallot create(LotteryBallot lotteryBallot);

    LotteryBallot findByUUID(UUID uuid);

    List<LotteryBallot> findAllByUser(User user);
}
