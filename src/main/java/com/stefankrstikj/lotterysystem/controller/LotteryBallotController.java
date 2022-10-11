package com.stefankrstikj.lotterysystem.controller;

import com.stefankrstikj.lotterysystem.model.response.LotteryBallotResponse;
import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/lottery/ballots")
public class LotteryBallotController {
    private final LotteryManagingService lotteryManagingService;

    public LotteryBallotController(LotteryManagingService lotteryManagingService) {
        this.lotteryManagingService = lotteryManagingService;
    }

    @GetMapping("/{uuid}")
    public LotteryBallotResponse getBallotByUUID(@PathVariable UUID uuid) {
        return lotteryManagingService.getLotteryBallotByUUID(uuid);
    }

    @PostMapping()
    public LotteryBallotResponse createBallot() {
        return lotteryManagingService.createLotteryBallot();
    }
}
