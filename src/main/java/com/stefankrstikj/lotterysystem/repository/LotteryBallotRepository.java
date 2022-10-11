package com.stefankrstikj.lotterysystem.repository;

import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryBallotRepository extends JpaRepository<LotteryBallot, Long> {
}
