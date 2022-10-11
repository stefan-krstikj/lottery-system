package com.stefankrstikj.lotterysystem.repository;

import com.stefankrstikj.lotterysystem.model.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Long> {
    Lottery findByDate(LocalDate date);
}
