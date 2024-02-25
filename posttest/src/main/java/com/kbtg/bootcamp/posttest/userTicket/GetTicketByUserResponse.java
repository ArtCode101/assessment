package com.kbtg.bootcamp.posttest.userTicket;

import java.util.List;

public record GetTicketByUserResponse(
        List<String> tickets,
        Integer count,
        Integer cost
){
}
