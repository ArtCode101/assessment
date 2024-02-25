package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LotteryService {

    @Autowired
    private LotteryRepository lotteryRepository;

    public GetLotteriesResponse getLotteries(){

        List<Lottery> lotteries = lotteryRepository.findAll();

        List<String> tickets = new ArrayList<>();
        lotteries.forEach(lottery -> {
            tickets.add(lottery.getTicket());
        });

        return new GetLotteriesResponse(tickets);
    }
}
