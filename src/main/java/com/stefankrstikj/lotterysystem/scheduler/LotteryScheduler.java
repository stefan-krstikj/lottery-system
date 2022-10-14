package com.stefankrstikj.lotterysystem.scheduler;

import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class LotteryScheduler {
    private final LotteryManagingService lotteryManagingService;

    public LotteryScheduler(LotteryManagingService lotteryManagingService) {
        this.lotteryManagingService = lotteryManagingService;
    }

    @Scheduled(cron = "1 0 0 * * *")
    public void drawWinners() {
        log.info("Started drawing winners schedule job at {}", LocalDateTime.now());
        lotteryManagingService.drawWinner();
        lotteryManagingService.startNewLottery();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        lotteryManagingService.startNewLottery();
    }
}
