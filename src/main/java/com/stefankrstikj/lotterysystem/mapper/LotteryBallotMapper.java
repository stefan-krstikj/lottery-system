package com.stefankrstikj.lotterysystem.mapper;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.response.LotteryBallotResponse;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LotteryBallotMapper {
    public LotteryBallotResponse entityToResponse(LotteryBallot lotteryBallot) {
        LotteryBallot winningBallot = lotteryBallot.getLottery().getWinningBallot();
        return new LotteryBallotResponse(
                lotteryBallot.getUuid(),
                lotteryBallot.getLottery().getId(),
                lotteryBallot.getLottery().getDate(),
                Objects.equals(winningBallot.getId(), lotteryBallot.getId())
        );
    }

}
