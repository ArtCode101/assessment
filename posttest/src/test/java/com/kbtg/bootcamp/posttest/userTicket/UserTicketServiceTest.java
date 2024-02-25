package com.kbtg.bootcamp.posttest.userTicket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbtg.bootcamp.posttest.exception.TicketNotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith({MockitoExtension.class})
public class UserTicketServiceTest {

    @Mock
    UserTicketRepository userTicketRepository;

    @Mock
    LotteryRepository lotteryRepository;

    UserTicketService userTicketService;

    @BeforeEach
    public void init(){
        userTicketService = new UserTicketService(userTicketRepository,lotteryRepository);
    }

    @Test
    @DisplayName("When Buy Ticket: must be success")
    public void  buyUserTicketTest() throws JsonProcessingException, JSONException {
        Lottery lottery = new Lottery("123456","123456",80,1);
        Optional<Lottery> lotteryOptional = Optional.ofNullable(lottery);
        Mockito.when(lotteryRepository.findById(Mockito.any())).thenReturn(lotteryOptional);

        UserTicket userTicket = new UserTicket();
        userTicket.setId(1);
        userTicket.setUserId("1");
        userTicket.setLottery(lottery);

        Mockito.when(userTicketRepository.save(Mockito.any(UserTicket.class))).thenReturn(userTicket);

        BuyUserTicketResponse response = userTicketService.buyUserTicket("1","123456");

        JSONAssert.assertEquals("{\"id\":\"1\"}",(new ObjectMapper()).writeValueAsString(response), JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    @DisplayName("When Buy Ticket: must be Ticket not found")
    public void  buyUserTicketFailTest(){
        Optional<Lottery> lotteryOptional = Optional.empty();
        Mockito.when(lotteryRepository.findById(Mockito.any())).thenReturn(lotteryOptional);

        try {
            userTicketService.buyUserTicket("1","123456");
            Assertions.assertTrue(false);
        }catch (TicketNotFoundException exception){
            Assertions.assertEquals("Ticket not found",exception.getMessage());
        }catch (Exception exception){
            Assertions.assertTrue(false);
        }
    }

    @Test
    @DisplayName("When Get Tickets: must be success")
    public void  getUserTicketTest() throws JsonProcessingException, JSONException {

        List<UserTicket> userTicketList = new ArrayList<>(List.of(
                new UserTicket(1,"1",new Lottery("123456","123456",80,1)),
                new UserTicket(2,"1",new Lottery("123457","123457",80,1)),
                new UserTicket(3,"1",new Lottery("123458","123458",80,1))
        ));

        Mockito.when(userTicketRepository.findByUserId(Mockito.any())).thenReturn(userTicketList);

        GetTicketByUserResponse response = userTicketService.getLotteryByUser("1");

        JSONAssert.assertEquals("{\"tickets\":[\"123456\",\"123457\",\"123458\"],\"count\":3,\"cost\":240}",(new ObjectMapper()).writeValueAsString(response), JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    @DisplayName("When Delete Tickets: must be success")
    public void  deleteUserTicketTest() throws JsonProcessingException, JSONException {

        List<UserTicket> userTicketList = new ArrayList<>(List.of(
                new UserTicket(1,"1",new Lottery("123456","123456",80,1))
        ));

        Mockito.when(userTicketRepository.findByUserAndTicket(Mockito.any(),Mockito.any())).thenReturn(userTicketList);

        DeleteTicketByUserResponse response = userTicketService.deleteTicketByUser("1","123456");

        JSONAssert.assertEquals("{\"ticket\":\"123456\"}",(new ObjectMapper()).writeValueAsString(response), JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    @DisplayName("When Delete Tickets: must be fail")
    public void  deleteUserTicketFailTest(){

        Mockito.when(userTicketRepository.findByUserAndTicket(Mockito.any(),Mockito.any())).thenReturn(List.of());

        try {
            userTicketService.deleteTicketByUser("1","123456");
            Assertions.assertTrue(false);
        }catch (TicketNotFoundException exception){
            Assertions.assertEquals("Ticket not found",exception.getMessage());
        }catch (Exception exception){
            Assertions.assertTrue(false);
        }

    }
}
