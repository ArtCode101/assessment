package com.kbtg.bootcamp.posttest.userTicket;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserTicketController {

    private UserTicketService userTicketService;

    public UserTicketController(UserTicketService userTicketService){
        this.userTicketService = userTicketService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public BuyUserTicketResponse BuyTicket(
            @PathVariable("userId")
            @Size(min = 1,max = 50,message = "Min length 1 and Max length 50")
            String userId,
            @PathVariable("ticketId")
            @Pattern(regexp = "[0-9]{6}",message = "Number only and fix length 6 digit")
            String ticketId
    ){
        return userTicketService.buyUserTicket(userId,ticketId);
    }

    @GetMapping("/users/{userId}/lotteries")
    public GetTicketByUserResponse getTicketByUser(
            @PathVariable("userId")
            @Size(min = 1,max = 50,message = "Min length 1 and Max length 50")
            String userId
    ){
        return userTicketService.getLotteryByUser(userId);
    }

    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public DeleteTicketByUserResponse deleteTicketByUser(
            @PathVariable("userId")
            @Size(min = 1,max = 50,message = "Min length 1 and Max length 50")
            String userId,
            @PathVariable("ticketId")
            @Pattern(regexp = "[0-9]{6}",message = "Number only and fix length 6 digit")
            String ticketId
    ){
        return userTicketService.deleteTicketByUser(userId,ticketId);
    }
}
