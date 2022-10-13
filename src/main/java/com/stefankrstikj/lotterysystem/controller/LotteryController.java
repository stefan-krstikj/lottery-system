package com.stefankrstikj.lotterysystem.controller;

import com.stefankrstikj.lotterysystem.exception.DateParseException;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;
import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/lottery")
public class LotteryController {
    private final LotteryManagingService lotteryManagingService;

    public LotteryController(LotteryManagingService lotteryManagingService) {
        this.lotteryManagingService = lotteryManagingService;
    }

    @Operation(summary = "Get the ongoing lottery")
    @GetMapping
    public LotteryResponse getOngoingLottery() {
        return lotteryManagingService.getOngoingLottery();
    }

    @Operation(summary = "Find a historic lottery for given date")
    @GetMapping("/{date}")
    public LotteryResponse getLotteryForDate(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return lotteryManagingService.getLotteryForDate(localDate);
        } catch (DateTimeParseException ex) {
            throw new DateParseException(date);
        }
    }
}
