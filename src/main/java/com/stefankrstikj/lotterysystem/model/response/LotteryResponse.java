package com.stefankrstikj.lotterysystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class LotteryResponse {
    private Long id;
    private LocalDate date;
    private String winner;

    public LotteryResponse(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }
}
