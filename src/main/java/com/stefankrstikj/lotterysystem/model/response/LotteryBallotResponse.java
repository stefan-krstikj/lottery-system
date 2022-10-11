package com.stefankrstikj.lotterysystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LotteryBallotResponse {
    private UUID uuid;
    private Long lotteryId;
    private LocalDate date;
    private boolean isWinner;
}
