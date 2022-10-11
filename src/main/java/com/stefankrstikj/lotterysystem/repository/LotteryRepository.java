package com.stefankrstikj.lotterysystem.repository;

import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.LotteryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Long> {
    Optional<Lottery> findByDateAndLotteryStatus(LocalDate date, LotteryStatus status);
}
