package com.stefankrstikj.lotterysystem.mapper;

import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;
import org.springframework.stereotype.Service;

@Service
public class LotteryMapper {
    private static final String NOT_DRAWN = "NOT_DRAWN";

    public LotteryResponse entityToResponse(Lottery lottery) {
        if (lottery.getWinningBallot() == null)
            return new LotteryResponse(lottery.getId(), lottery.getDate(), NOT_DRAWN);
        return new LotteryResponse(lottery.getId(), lottery.getDate(), lottery.getWinningBallot().getUuid().toString());
    }
}
