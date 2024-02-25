package com.kbtg.bootcamp.posttest.userTicket;

import com.kbtg.bootcamp.posttest.exception.TicketNotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserTicketService {

    private UserTicketRepository userTicketRepository;

    private LotteryRepository lotteryRepository;

    public UserTicketService (UserTicketRepository userTicketRepository,LotteryRepository lotteryRepository){
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }


    @Transactional
    public BuyUserTicketResponse buyUserTicket(String userId, String ticketId){

        Optional<Lottery> lottery = lotteryRepository.findById(ticketId);
        if (lottery.isEmpty()){
            throw new TicketNotFoundException("Ticket not found");
        }

        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(userId);
        userTicket.setLottery(lottery.get());
        userTicketRepository.save(userTicket);

        return  new BuyUserTicketResponse(String.valueOf(userTicket.getId()));
    }

    public GetTicketByUserResponse getLotteryByUser(String userId){

        List<UserTicket> userTickets = userTicketRepository.findByUserId(userId);
        return new GetTicketByUserResponse(
                getListLottery(userTickets),
                getCountLottery(userTickets),
                getCostLottery(userTickets)
        );
    }

    private List<String> getListLottery(List<UserTicket> userTickets){
        List<String> listLottery = new ArrayList<>();
        for (UserTicket userTicket : userTickets){
            listLottery.add(userTicket.getLottery().getTicket());
        }
        return listLottery;
    }

    private Integer getCountLottery(List<UserTicket> userTickets){
        Integer count = 0;
        for (UserTicket userTicket : userTickets){
            count += userTicket.getLottery().getAmount();
        }
        return count;
    }

    private Integer getCostLottery(List<UserTicket> userTickets){
        Integer cost = 0;
        for (UserTicket userTicket : userTickets){
            Integer amount = userTicket.getLottery().getAmount();
            Integer Price = userTicket.getLottery().getPrice();
            cost += (amount *Price);
        }
        return cost;
    }

    @Transactional
    public DeleteTicketByUserResponse deleteTicketByUser(String userId, String ticketId) {

        List<UserTicket> userTickets = userTicketRepository.findByUserAndTicket(userId,ticketId);

        if (userTickets.isEmpty()){
            throw new TicketNotFoundException("Ticket not found");
        }

        userTicketRepository.deleteAll(userTickets);

        return new  DeleteTicketByUserResponse(ticketId);
    }
}
