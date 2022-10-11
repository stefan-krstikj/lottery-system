package com.stefankrstikj.lotterysystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ballot")
@Getter
@Setter
public class LotteryBallot {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ManyToOne
    private Lottery lottery;

    @ManyToOne
    private User participant;

    public LotteryBallot(Lottery lottery, User participant) {
        this.lottery = lottery;
        this.participant = participant;
    }

    public LotteryBallot() {

    }
}
