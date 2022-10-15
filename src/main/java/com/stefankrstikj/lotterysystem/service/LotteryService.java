package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.LotteryBallot;

import java.time.LocalDate;

/**
 * Service class for  {@link Lottery}. This service should not be accessed directly, and instead please use
 * {@link LotteryManagingService} to access the underlying logic here
 */
public interface LotteryService {
    Lottery save(Lottery lottery);

    Lottery getLotteryForDate(LocalDate localDate);

    Lottery getOngoingLottery();

    boolean isLotteryActive();
}
