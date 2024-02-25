package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotteryController {

    private LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService){
        this.lotteryService = lotteryService;
    }

    @GetMapping("/lotteries")
    public GetLotteriesResponse getLotteries(){
        return lotteryService.getLotteries();
    }
}
