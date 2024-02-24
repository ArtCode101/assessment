package com.kbtg.bootcamp.posttest.lottery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "lottery")
@AllArgsConstructor
@NoArgsConstructor
public class Lottery {

    @Id
    @Column(name = "id",length = 6, nullable = false)
    private String id;

    @Column(name = "ticket",length = 6,nullable = false)
    private String ticket;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "amount",nullable = false)
    private Integer amount;

}
