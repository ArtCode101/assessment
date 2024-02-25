package com.kbtg.bootcamp.posttest.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/lotteries")
    public AdminAddTicketResponse addLottery(
            @RequestBody AdminAddTicketRequest request
    ){
        return   adminService.addLottery(request);
    }
}
