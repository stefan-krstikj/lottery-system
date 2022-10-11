package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.model.Lottery;

import java.time.LocalDate;

public interface LotteryService {
    Lottery save(Lottery lottery);

    Lottery findLotteryByDate(LocalDate localDate);
}
