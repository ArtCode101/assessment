package com.kbtg.bootcamp.posttest.userTicket;

import com.kbtg.bootcamp.posttest.exception.TicketNotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTicketService {

    private UserTicketRepository userTicketRepository;

    private LotteryRepository lotteryRepository;

    public UserTicketService (UserTicketRepository userTicketRepository,LotteryRepository lotteryRepository){
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }


    public BuyUserTicketResponse buyUserTicket(String userId, String ticketId){

        Optional<Lottery> lottery = lotteryRepository.findById(ticketId);
        if (lottery.isEmpty()){
            throw new TicketNotFoundException("Ticket not found");
        }

        UserTicket userTicket = new UserTicket();
        userTicket.setUser_id(userId);
        userTicket.setLottery(lottery.get());
        userTicketRepository.save(userTicket);

        return  new BuyUserTicketResponse(String.valueOf(userTicket.getId()));
    }
}
