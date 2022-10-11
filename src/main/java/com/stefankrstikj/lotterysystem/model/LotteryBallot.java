package com.stefankrstikj.lotterysystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ballot")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class LotteryBallot {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "lottery_id")
    private Lottery lottery;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private User participant;

    public LotteryBallot(Lottery lottery, User participant, UUID uuid) {
        this.lottery = lottery;
        this.participant = participant;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "LotteryBallot{" +
                "uuid=" + uuid +
                ", lottery=" + lottery.getId() +
                '}';
    }
}
