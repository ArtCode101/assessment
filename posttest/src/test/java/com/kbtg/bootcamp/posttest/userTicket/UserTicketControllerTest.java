package com.kbtg.bootcamp.posttest.userTicket;

import com.kbtg.bootcamp.posttest.exception.ApiExceptionHandler;
import com.kbtg.bootcamp.posttest.exception.TicketNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
public class UserTicketControllerTest {

    MockMvc mockMvc;

    @Mock
    UserTicketService userTicketService;

    @BeforeEach
    public void init(){
        UserTicketController userTicketController = new UserTicketController(userTicketService);
        mockMvc = MockMvcBuilders.standaloneSetup(userTicketController).setControllerAdvice(new ApiExceptionHandler()).build();
    }

    @Test
    @DisplayName("When Perform on Post: /users/1234567890/lotteries/123456 should be status code 201")
    public void buyTicketTest() throws Exception {
        mockMvc.perform(post("/users/1234567890/lotteries/123456")).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("When Perform on Post: /users/1234567890/lotteries/123456 should be status code 404")
    public void buyTicketNotFoundTest() throws Exception {
        Mockito.when(userTicketService.buyUserTicket(Mockito.any(),Mockito.any())).thenThrow(new TicketNotFoundException("Ticket not found"));
        mockMvc.perform(post("/users/1234567890/lotteries/123456")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("When Perform on Post: /users/1234567890/lotteries/12345 should be status code 400")
    public void buyTicketFailTicketTest() throws Exception {
        mockMvc.perform(post("/users/1/lotteries/12345")).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("When Perform on Post: /users/123456789/lotteries/123456 should be status code 400")
    public void buyTicketFailUserTest() throws Exception {
        mockMvc.perform(post("/users/123456789/lotteries/123456")).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("When Perform on Get: /users/1234567890/lotteries should be status code 200")
    public void getTicketsTest() throws Exception {
        mockMvc.perform(get("/users/1234567890/lotteries")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("When Perform on Delete: /users/1234567890/lotteries/123456 should be status code 200")
    public void deleteTicketsTest() throws Exception {
        mockMvc.perform(delete("/users/1234567890/lotteries/123456")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("When Perform on Delete: /users/1234567890/lotteries/12345 should be status code 400")
    public void deleteTicketsFailTest() throws Exception {
        mockMvc.perform(delete("/users/1234567890/lotteries/12345")).andExpect(status().isBadRequest());
    }
}
