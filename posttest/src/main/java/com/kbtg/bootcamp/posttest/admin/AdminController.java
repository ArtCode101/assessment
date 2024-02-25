package com.kbtg.bootcamp.posttest.admin;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/admin/lotteries")
    public AdminAddTicketResponse addLottery(
            @Validated @RequestBody AdminAddTicketRequest request
    ){
        return   adminService.addLottery(request);
    }
}
