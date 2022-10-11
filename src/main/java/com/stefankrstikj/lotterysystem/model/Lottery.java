package com.stefankrstikj.lotterysystem.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lottery")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Lottery {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private LotteryStatus lotteryStatus;

    @OneToMany(mappedBy = "lottery")
    private List<LotteryBallot> ballots = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "winning_ballot_id")
    private LotteryBallot winningBallot;

    public Lottery(LocalDate date) {
        this.date = date;
        this.lotteryStatus = LotteryStatus.OPEN;
    }
}
