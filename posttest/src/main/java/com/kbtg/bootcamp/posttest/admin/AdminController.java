package com.kbtg.bootcamp.posttest.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @PostMapping("/admin/lotteries")
    public AdminAddTicketResponse addLottery(
            AdminAddTicketRequest request
    ){
        return  null;
    }
}
