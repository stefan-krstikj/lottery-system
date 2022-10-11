package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.mapper.LotteryMapper;
import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.LotteryStatus;
import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;
import com.stefankrstikj.lotterysystem.service.LotteryBallotService;
import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import com.stefankrstikj.lotterysystem.service.LotteryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LotteryManagingServiceImpl implements LotteryManagingService {
    private final LotteryService lotteryService;
    private final LotteryBallotService lotteryBallotService;
    private final LotteryMapper mapper;

    public LotteryManagingServiceImpl(LotteryService lotteryService, LotteryBallotService lotteryBallotService,
                                      LotteryMapper mapper) {
        this.lotteryService = lotteryService;
        this.lotteryBallotService = lotteryBallotService;
        this.mapper = mapper;
    }

    @Override
    public UUID createLotteryBallot() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            Lottery ongoingLottery = lotteryService.findLotteryByDate(LocalDate.now());
            LotteryBallot lotteryBallot = new LotteryBallot(ongoingLottery, user);
            return lotteryBallotService.create(lotteryBallot).getUuid();
        } else {
            throw new RuntimeException("Internal server error");
        }
    }

    @Override
    public boolean isWinningLotteryBallot(LotteryBallot lotteryBallot) {
        return lotteryBallot.getUuid().equals(lotteryBallot.getLottery().getWinningBallot().getUuid());
    }

    @Override
    @Transactional
    public Optional<UUID> drawWinner() {
        Lottery ongoingLottery = lotteryService.findLotteryByDate(LocalDate.now());
        LotteryBallot winner = null;
        try {
            winner = chooseRandomBallot(ongoingLottery);
        } catch (Exception e) {
            // todo log
        }

        ongoingLottery.setLotteryStatus(LotteryStatus.CLOSED);
        lotteryService.save(ongoingLottery);
        if (winner == null)
            return Optional.empty();
        return Optional.of(winner.getUuid());
    }

    private LotteryBallot chooseRandomBallot(Lottery lottery) {
        // todo exception if no ballots
        List<LotteryBallot> ballots = new ArrayList<>(lottery.getBallots());
        return ballots.get(0);
    }

    @Override
    public LotteryResponse startNewLottery() {
        // todo check if there is an ongoing lottery
        Lottery lottery = new Lottery(LocalDate.now());
        Lottery savedLottery = lotteryService.save(lottery);
        return mapper.entityToResponse(savedLottery);
    }

    @Override
    public LotteryResponse getCurrentLottery() {
        return mapper.entityToResponse(lotteryService.findLotteryByDate(LocalDate.now()));
    }
}