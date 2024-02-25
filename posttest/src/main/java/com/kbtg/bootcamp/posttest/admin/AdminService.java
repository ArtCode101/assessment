package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private LotteryRepository lotteryRepository;

    public AdminService(LotteryRepository lotteryRepository){
        this.lotteryRepository = lotteryRepository;
    }

    public AdminAddTicketResponse addLottery(AdminAddTicketRequest request){
        Lottery lottery = new Lottery(
                request.ticket(),
                request.ticket(),
                request.price() == null? 80 : request.price(),
                request.amount() == null? 1 : request.amount()
                );

        lotteryRepository.save(lottery);

        return new AdminAddTicketResponse(lottery.getTicket());
    }
}
