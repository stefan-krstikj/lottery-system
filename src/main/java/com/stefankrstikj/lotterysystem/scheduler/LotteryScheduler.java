package com.stefankrstikj.lotterysystem.scheduler;

import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LotteryScheduler {
    private final LotteryManagingService lotteryManagingService;

    public LotteryScheduler(LotteryManagingService lotteryManagingService) {
        this.lotteryManagingService = lotteryManagingService;
    }

    @Scheduled(cron = "0 0/5 * 1/1 * ?")
    public void drawWinners() {
        lotteryManagingService.drawWinner();
        lotteryManagingService.startNewLottery();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        lotteryManagingService.startNewLottery();
    }
}
