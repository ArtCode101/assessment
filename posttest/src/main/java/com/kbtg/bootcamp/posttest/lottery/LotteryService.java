package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LotteryService {

    private LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository){
        this.lotteryRepository = lotteryRepository;
    }

    public GetLotteriesResponse getLotteries(){

        List<Lottery> lotteries = lotteryRepository.findAll();

        List<String> tickets = new ArrayList<>();
        lotteries.forEach(lottery -> {
            tickets.add(lottery.getTicket());
        });

        return new GetLotteriesResponse(tickets);
    }
}
