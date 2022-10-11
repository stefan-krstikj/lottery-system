package com.stefankrstikj.lotterysystem.controller;

import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;
import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/lottery")
public class LotteryController {
    private final LotteryManagingService lotteryManagingService;

    public LotteryController(LotteryManagingService lotteryManagingService) {
        this.lotteryManagingService = lotteryManagingService;
    }

    @GetMapping()
    public LotteryResponse getOngoingLottery() {
        return lotteryManagingService.getOngoingLottery();
    }

    @GetMapping("/{date}")
    public LotteryResponse getLotteryForDate(@PathVariable LocalDate date) {
        return lotteryManagingService.getLotteryForDate(date);
    }
}
