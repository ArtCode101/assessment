package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
public class AdminServiceTest {

    @Mock
    LotteryRepository lotteryRepository;

    AdminService adminService;

    @BeforeEach
    public void init(){
         adminService = new AdminService(lotteryRepository);
    }

    @Test
    @DisplayName("When addLottery By admin: must be ticket 123456")
    public void addLotteryTest(){

        AdminAddTicketRequest request = new AdminAddTicketRequest("123456",80,1);

        AdminAddTicketResponse response = adminService.addLottery(request);

        Assertions.assertEquals("123456",response.ticket());
    }
}
