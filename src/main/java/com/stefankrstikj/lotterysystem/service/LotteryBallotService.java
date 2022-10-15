package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.User;

import java.util.List;
import java.util.UUID;

/**
 * Service class for  {@link LotteryBallot}. This service should not be accessed directly, and instead please use
 * {@link LotteryManagingService} to access the underlying logic here
 */
public interface LotteryBallotService {
    LotteryBallot create(LotteryBallot lotteryBallot);

    LotteryBallot findByUUID(UUID uuid);

    List<LotteryBallot> findAllByUser(User user);
}
