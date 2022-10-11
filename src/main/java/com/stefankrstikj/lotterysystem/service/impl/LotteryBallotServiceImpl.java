package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.repository.LotteryBallotRepository;
import com.stefankrstikj.lotterysystem.service.LotteryBallotService;
import org.springframework.stereotype.Service;

@Service
public class LotteryBallotServiceImpl implements LotteryBallotService {
    private final LotteryBallotRepository lotteryBallotRepository;

    public LotteryBallotServiceImpl(LotteryBallotRepository lotteryBallotRepository) {
        this.lotteryBallotRepository = lotteryBallotRepository;
    }

    @Override
    public LotteryBallot create(LotteryBallot lotteryBallot) {
        return lotteryBallotRepository.save(lotteryBallot);
    }
}
