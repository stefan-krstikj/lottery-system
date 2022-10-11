package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.repository.LotteryRepository;
import com.stefankrstikj.lotterysystem.service.LotteryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LotteryServiceImpl implements LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryServiceImpl(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    @Override
    public Lottery save(Lottery lottery) {
        return lotteryRepository.save(lottery);
    }

    @Override
    public Lottery findLotteryByDate(LocalDate localDate) {
        return lotteryRepository.findByDate(localDate);
    }
}
