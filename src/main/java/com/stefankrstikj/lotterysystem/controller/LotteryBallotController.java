package com.stefankrstikj.lotterysystem.controller;

import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/lottery/ballots")
public class LotteryBallotController {
    private final LotteryManagingService lotteryManagingService;

    public LotteryBallotController(LotteryManagingService lotteryManagingService) {
        this.lotteryManagingService = lotteryManagingService;
    }

    @PostMapping()
    public UUID createBallot() {
        return lotteryManagingService.createLotteryBallot();
    }
}
