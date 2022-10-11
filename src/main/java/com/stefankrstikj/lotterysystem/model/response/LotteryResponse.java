package com.stefankrstikj.lotterysystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LotteryResponse {
    private Long id;
    private LocalDate date;
    private UUID winnerId;

    public LotteryResponse(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }
}
