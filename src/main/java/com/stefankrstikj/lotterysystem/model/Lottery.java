package com.stefankrstikj.lotterysystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lottery")
@Getter
@Setter
public class Lottery {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Enumerated
    private LotteryStatus lotteryStatus;

    @OneToMany
    private Set<LotteryBallot> ballots;

    @ManyToOne
    private LotteryBallot winningBallot;

    public Lottery(LocalDate date) {
        this.date = date;
        this.ballots = new HashSet<>();
        this.lotteryStatus = LotteryStatus.OPEN;
    }

    public Lottery() {

    }
}
