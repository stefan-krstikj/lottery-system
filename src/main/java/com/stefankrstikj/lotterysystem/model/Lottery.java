package com.stefankrstikj.lotterysystem.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lottery")
@Getter
@Setter
@NoArgsConstructor
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private LotteryStatus lotteryStatus;

    @OneToMany(mappedBy = "lottery")
    private Set<LotteryBallot> ballots = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "winning_ballot_id")
    private LotteryBallot winningBallot;

    public Lottery(LocalDate date) {
        this.date = date;
        this.lotteryStatus = LotteryStatus.OPEN;
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "id=" + id +
                ", date=" + date +
                ", lotteryStatus=" + lotteryStatus +
                '}';
    }
}
