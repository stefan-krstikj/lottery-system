package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.repository.LotteryRepository;
import com.stefankrstikj.lotterysystem.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Slf4j
@Service
public class LotteryServiceImpl implements LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryServiceImpl(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    @Override
    public Lottery save(Lottery lottery) {
        log.info("Saving lottery {}", lottery);
        return lotteryRepository.save(lottery);
    }

    @Override
    public Lottery findLotteryByDate(LocalDate localDate) {
        return lotteryRepository.findByDate(localDate)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean isLotteryActive() {
        return lotteryRepository.findByDate(LocalDate.now()).isPresent();
    }
}
