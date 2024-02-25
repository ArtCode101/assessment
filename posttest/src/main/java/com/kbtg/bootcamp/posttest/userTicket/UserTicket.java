package com.kbtg.bootcamp.posttest.userTicket;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_ticket")
public class UserTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ticket_id",referencedColumnName = "id")
    private Lottery lottery;

}
