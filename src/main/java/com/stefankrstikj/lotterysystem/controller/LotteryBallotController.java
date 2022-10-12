package com.stefankrstikj.lotterysystem.controller;

import com.stefankrstikj.lotterysystem.model.response.LotteryBallotResponse;
import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lottery/ballots")
public class LotteryBallotController {
    private final LotteryManagingService lotteryManagingService;

    public LotteryBallotController(LotteryManagingService lotteryManagingService) {
        this.lotteryManagingService = lotteryManagingService;
    }

    @Operation(summary = "Get Lottery Ballot by UUID")
    @GetMapping("/{uuid}")
    public LotteryBallotResponse getBallotByUUID(@PathVariable UUID uuid) {
        return lotteryManagingService.getLotteryBallotByUUID(uuid);
    }

    @Operation(summary = "Get all ballots for current user")
    @GetMapping()
    public List<LotteryBallotResponse> getAllBallots() {
        return lotteryManagingService.getAllBallots();
    }

    @Operation(summary = "Submit a new ballot for the ongoing lottery for current user")
    @PostMapping()
    public LotteryBallotResponse createBallot() {
        return lotteryManagingService.createLotteryBallot();
    }
}
