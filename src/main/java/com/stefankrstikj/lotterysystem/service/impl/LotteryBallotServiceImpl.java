package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.exception.NotFoundException;
import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.repository.LotteryBallotRepository;
import com.stefankrstikj.lotterysystem.service.LotteryBallotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@Service
public class LotteryBallotServiceImpl implements LotteryBallotService {
    private final LotteryBallotRepository lotteryBallotRepository;

    public LotteryBallotServiceImpl(LotteryBallotRepository lotteryBallotRepository) {
        this.lotteryBallotRepository = lotteryBallotRepository;
    }

    @Override
    public LotteryBallot create(LotteryBallot lotteryBallot) {
        log.info("Saving lottery ballot {}", lotteryBallot);
        return lotteryBallotRepository.save(lotteryBallot);
    }

    @Override
    public LotteryBallot findByUUID(UUID uuid) {
        return lotteryBallotRepository
                .findLotteryBallotByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(LotteryBallot.class, uuid));
    }
}
