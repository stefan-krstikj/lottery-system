package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.exception.OngoingLotteryNotFoundException;
import com.stefankrstikj.lotterysystem.mapper.LotteryMapper;
import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.LotteryStatus;
import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;
import com.stefankrstikj.lotterysystem.service.LotteryBallotService;
import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import com.stefankrstikj.lotterysystem.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Slf4j
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
        Lottery ongoingLottery = lotteryService.findLotteryByDate(LocalDate.now());

        if (ongoingLottery == null)
            throw new OngoingLotteryNotFoundException();

        if (principal instanceof User) {
            User user = (User) principal;
            LotteryBallot lotteryBallot = new LotteryBallot(ongoingLottery, user, UUID.randomUUID());
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
            log.error(e.getLocalizedMessage());
        }
        log.info("Winner {} drawn for lottery: {}", winner, ongoingLottery.getId());
        ongoingLottery.setLotteryStatus(LotteryStatus.CLOSED);
        ongoingLottery.setWinningBallot(winner);
        lotteryService.save(ongoingLottery);
        if (winner == null)
            return Optional.empty();
        return Optional.of(winner.getUuid());
    }

    private LotteryBallot chooseRandomBallot(Lottery lottery) {
        if (lottery.getBallots().size() == 0) {
            return null;
        }

        Random random = new Random();
        int randomWinner = random.nextInt(lottery.getBallots().size() + 1);
        return lottery.getBallots().get(randomWinner);
    }

    @Override
    public LotteryResponse startNewLottery() {
        if (lotteryService.isLotteryActive())
            return null;

        Lottery lottery = new Lottery(LocalDate.now());
        Lottery savedLottery = lotteryService.save(lottery);
        log.info("Started new lottery: {}", lottery);
        return mapper.entityToResponse(savedLottery);
    }

    @Override
    public LotteryResponse getLotteryForDate(LocalDate date) {
        return mapper.entityToResponse(lotteryService.findLotteryByDate(date));
    }
}
