package com.stefankrstikj.lotterysystem.scheduler;

import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LotteryScheduler {
    private final LotteryManagingService lotteryManagingService;

    public LotteryScheduler(LotteryManagingService lotteryManagingService) {
        this.lotteryManagingService = lotteryManagingService;
    }

    @Scheduled(fixedDelay = 10000)
    public void drawWinners() {
        lotteryManagingService.drawWinner();
        lotteryManagingService.startNewLottery();
    }
}
