package com.stefankrstikj.lotterysystem.repository;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.LotteryStatus;
import com.stefankrstikj.lotterysystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LotteryBallotRepository extends JpaRepository<LotteryBallot, Long> {
    Optional<LotteryBallot> findLotteryBallotByUuid(UUID uuid);

    List<LotteryBallot> findLotteryBallotsByParticipantAndLotteryLotteryStatus(User participant,
                                                                               LotteryStatus lotteryStatus);
}
