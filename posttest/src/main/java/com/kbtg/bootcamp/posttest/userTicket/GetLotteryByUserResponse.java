package com.kbtg.bootcamp.posttest.userTicket;

import java.util.List;

public record GetLotteryByUserResponse (
        List<String> tickets,
        Integer count,
        Integer cost
){
}
