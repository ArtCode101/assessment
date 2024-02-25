package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;

    @GetMapping("/lotteries")
    public GetLotteriesResponse getLotteries(){
        return lotteryService.getLotteries();
    }
}
